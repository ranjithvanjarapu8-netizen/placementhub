package com.placementhub.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.placementhub.dto.GeminiJobAnalysisDto;
import com.placementhub.dto.JobAiAnalysisDto;
import com.placementhub.dto.SupersetJobProfileDetailsDto;

import com.placementhub.entity.JobAiAnalysis;
import com.placementhub.entity.JobDocument;
import com.placementhub.entity.JobProfile;

import com.placementhub.repository.JobAiAnalysisRepository;
import com.placementhub.repository.JobDocumentRepository;
import com.placementhub.repository.JobProfileRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobAiAnalysisService {

    private final JobAiAnalysisRepository jobAiAnalysisRepository;

    private final SupersetClient supersetClient;

    private final GeminiService geminiService;

    private final PdfTextExtractionService pdfTextExtractionService;

    private final ObjectMapper objectMapper;

    private final JobProfileRepository jobProfileRepository;

    private final JobDocumentRepository jobDocumentRepository;

    public JobAiAnalysisService(

            JobAiAnalysisRepository jobAiAnalysisRepository,

            SupersetClient supersetClient,

            PdfTextExtractionService pdfTextExtractionService,

            GeminiService geminiService,

            ObjectMapper objectMapper,

            JobProfileRepository jobProfileRepository,

            JobDocumentRepository jobDocumentRepository

    ) {

        this.jobAiAnalysisRepository =
                jobAiAnalysisRepository;

        this.supersetClient =
                supersetClient;

        this.pdfTextExtractionService =
                pdfTextExtractionService;

        this.geminiService =
                geminiService;

        this.objectMapper =
                objectMapper;

        this.jobProfileRepository =
                jobProfileRepository;

        this.jobDocumentRepository =
                jobDocumentRepository;
    }


    // =====================================================
    // GET EXISTING AI ANALYSIS
    // =====================================================

    public JobAiAnalysisDto getAnalysis(
            Long jobProfileId
    ) {

        JobAiAnalysis analysis =
                jobAiAnalysisRepository
                        .findByJobProfileId(jobProfileId)
                        .orElse(null);

        if (analysis == null) {
            return null;
        }

        return toDto(analysis);
    }


    // =====================================================
    // ENTITY -> DTO
    // =====================================================

    private JobAiAnalysisDto toDto(
            JobAiAnalysis analysis
    ) {

        JobAiAnalysisDto dto =
                new JobAiAnalysisDto();

        dto.setId(
                analysis.getId()
        );

        dto.setJobProfileId(
                analysis
                        .getJobProfile()
                        .getId()
        );

        dto.setSummary(
                analysis.getSummary()
        );

        dto.setRequiredSkills(
                analysis.getRequiredSkills()
        );

        dto.setOaTopics(
                analysis.getOaTopics()
        );

        dto.setOaDifficulty(
                analysis.getOaDifficulty()
        );

        dto.setPreparationTopics(
                analysis.getPreparationTopics()
        );

        dto.setAnalysis(
                analysis.getAnalysis()
        );

        dto.setAnalyzedAt(
                analysis.getAnalyzedAt()
        );

        return dto;
    }


    // =====================================================
    // MAIN METHOD
    //
    // ONE API CALL DOES EVERYTHING
    // =====================================================

    @Transactional
    public JobAiAnalysisDto getOrGenerateAiAnalysis(

            Long jobProfileId,

            String studentId,

            String authorization

    ) {

        // =================================================
        // 1. CHECK EXISTING AI ANALYSIS
        // =================================================

        JobAiAnalysis existing =
                jobAiAnalysisRepository
                        .findByJobProfileId(
                                jobProfileId
                        )
                        .orElse(null);

        if (existing != null) {

            // Already analyzed.
            // Return immediately.

            return toDto(existing);
        }


        // =================================================
        // 2. FIND LOCAL JOB PROFILE
        // =================================================

        JobProfile jobProfile =
                jobProfileRepository
                        .findById(jobProfileId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Job profile not found: "
                                                + jobProfileId
                                )
                        );


        // =================================================
        // 3. CHECK LOCAL DOCUMENT
        // =================================================

        List<JobDocument> documents =
                jobDocumentRepository
                        .findByJobProfileId(
                                jobProfileId
                        );

        String jobDescription = null;


        // =================================================
        // 4. IF LOCAL PDF EXISTS
        // =================================================

        if (!documents.isEmpty()) {

            JobDocument document =
                    documents.get(0);

            try {

                jobDescription =
                        extractJobDescriptionText(

                                studentId,

                                jobProfile
                                        .getSupersetJobProfileId(),

                                document
                                        .getSupersetDocumentId(),

                                authorization
                        );

            } catch (Exception e) {

                /*
                 * PDF exists but could not be downloaded/extracted.
                 *
                 * Don't stop the entire analysis.
                 *
                 * We will use the fallback information below.
                 */

                jobDescription = null;
            }
        }


        // =================================================
        // 5. IF LOCAL PDF DOES NOT EXIST
        //    TRY TO GET DOCUMENT FROM SUPERSET
        // =================================================

        if (jobDescription == null
                || jobDescription.isBlank()) {

            boolean documentsFound =
                    syncJobDocuments(
                            jobProfile,
                            studentId,
                            authorization
                    );

            if (documentsFound) {

                // Fetch documents again after syncing.

                documents =
                        jobDocumentRepository
                                .findByJobProfileId(
                                        jobProfileId
                                );

                if (!documents.isEmpty()) {

                    JobDocument document =
                            documents.get(0);

                    try {

                        jobDescription =
                                extractJobDescriptionText(

                                        studentId,

                                        jobProfile
                                                .getSupersetJobProfileId(),

                                        document
                                                .getSupersetDocumentId(),

                                        authorization
                                );

                    } catch (Exception e) {

                        /*
                         * Superset document exists but
                         * PDF download/extraction failed.
                         *
                         * Use fallback information.
                         */

                        jobDescription = null;
                    }
                }
            }
        }


        // =================================================
        // 6. PDF NOT AVAILABLE
        //
        // USE COMPANY + ROLE + OTHER LOCAL INFORMATION
        // =================================================

        if (jobDescription == null
                || jobDescription.isBlank()) {

            jobDescription =
                    buildFallbackJobInformation(
                            jobProfile
                    );
        }


        // =================================================
        // 7. SEND INFORMATION TO GEMINI
        // =================================================

        GeminiJobAnalysisDto aiResult =
                analyzeJobDescriptionAsDto(
                        jobDescription
                );


        // =================================================
        // 8. CREATE AI ANALYSIS ENTITY
        // =================================================

        JobAiAnalysis analysis =
                new JobAiAnalysis();

        analysis.setJobProfile(
                jobProfile
        );

        analysis.setSummary(
                aiResult.getSummary()
        );

        analysis.setRequiredSkills(
                aiResult.getRequiredSkills()
        );

        analysis.setOaTopics(
                aiResult.getOaTopics()
        );

        analysis.setOaDifficulty(
                aiResult.getOaDifficulty()
        );

        analysis.setPreparationTopics(
                aiResult.getPreparationTopics()
        );

        analysis.setAnalysis(
                aiResult.getAnalysis()
        );

        analysis.setAnalyzedAt(
                LocalDateTime.now()
        );


        // =================================================
        // 9. SAVE AI ANALYSIS
        // =================================================

        JobAiAnalysis saved =
                jobAiAnalysisRepository.save(
                        analysis
                );


        // =================================================
        // 10. RETURN FINAL RESULT
        // =================================================

        return toDto(saved);
    }


    // =====================================================
    // BUILD FALLBACK INFORMATION
    //
    // USED WHEN PDF IS NOT AVAILABLE
    // =====================================================

    private String buildFallbackJobInformation(

            JobProfile jobProfile

    ) {

        StringBuilder information =
                new StringBuilder();


        information.append(
                "The official job description PDF "
                        + "is not available."
        );

        information.append("\n\n");


        // =================================================
        // COMPANY
        // =================================================

        if (jobProfile.getCompany() != null) {

            information.append(
                    "Company: "
            );

            information.append(
                    jobProfile
                            .getCompany()
                            .getName()
            );

            information.append("\n");
        }


        // =================================================
        // JOB ROLE
        // =================================================

        if (jobProfile.getTitle() != null) {

            information.append(
                    "Job Role: "
            );

            information.append(
                    jobProfile.getTitle()
            );

            information.append("\n");
        }


        // =================================================
        // LOCATION
        // =================================================

        if (jobProfile.getLocation() != null) {

            information.append(
                    "Location: "
            );

            information.append(
                    jobProfile.getLocation()
            );

            information.append("\n");
        }


        // =================================================
        // POSITION TYPE
        // =================================================

        if (jobProfile.getPositionType() != null) {

            information.append(
                    "Position Type: "
            );

            information.append(
                    jobProfile.getPositionType()
            );

            information.append("\n");
        }


        // =================================================
        // PLACEMENT
        // =================================================

        if (jobProfile.getPlacementName() != null) {

            information.append(
                    "Placement: "
            );

            information.append(
                    jobProfile.getPlacementName()
            );

            information.append("\n");
        }


        // =================================================
        // CATEGORY
        // =================================================

        if (jobProfile.getPlacementCategoryName() != null) {

            information.append(
                    "Placement Category: "
            );

            information.append(
                    jobProfile
                            .getPlacementCategoryName()
            );

            information.append("\n");
        }


        // =================================================
        // STATUS
        // =================================================

        if (jobProfile.getStatus() != null) {

            information.append(
                    "Status: "
            );

            information.append(
                    jobProfile.getStatus()
            );

            information.append("\n");
        }


        // =================================================
        // AVAILABLE JOB DESCRIPTION
        //
        // Sometimes Superset already stores a JD in
        // job_profiles.job_description even when there
        // is no PDF.
        // =================================================

        if (jobProfile.getJobDescription() != null
                && !jobProfile
                        .getJobDescription()
                        .isBlank()) {

            information.append("\n");

            information.append(
                    "Available Job Description Information:\n"
            );

            information.append(
                    jobProfile.getJobDescription()
            );
        }


        // =================================================
        // CTC INFORMATION
        // =================================================

        if (jobProfile.getCtcAdditionalInfo() != null
                && !jobProfile
                        .getCtcAdditionalInfo()
                        .isBlank()) {

            information.append("\n\n");

            information.append(
                    "Compensation Information:\n"
            );

            information.append(
                    jobProfile
                            .getCtcAdditionalInfo()
            );
        }


        // =================================================
        // CTC MIN/MAX
        // =================================================

        if (jobProfile.getCtcMin() != null) {

            information.append("\n");

            information.append(
                    "Minimum CTC: "
            );

            information.append(
                    jobProfile.getCtcMin()
            );
        }

        if (jobProfile.getCtcMax() != null) {

            information.append("\n");

            information.append(
                    "Maximum CTC: "
            );

            information.append(
                    jobProfile.getCtcMax()
            );
        }


        return information.toString();
    }


    // =====================================================
    // SYNC DOCUMENTS FROM SUPERSET
    //
    // RETURNS TRUE  -> documents found
    // RETURNS FALSE -> no documents found
    // =====================================================

    private boolean syncJobDocuments(

            JobProfile jobProfile,

            String studentId,

            String authorization

    ) {

        SupersetJobProfileDetailsDto details;

        try {

            details =
                    supersetClient.getJobProfileDetails(

                            studentId,

                            jobProfile
                                    .getSupersetJobProfileId(),

                            authorization
                    );

        } catch (Exception e) {

            /*
             * Superset failed.
             *
             * Don't fail the whole AI analysis.
             * The caller will use fallback information.
             */

            return false;
        }


        if (details == null) {

            return false;
        }


        List<SupersetJobProfileDetailsDto.DocumentDto>
                documents =
                new ArrayList<>();


        // =================================================
        // 1. CHECK TOP-LEVEL DOCUMENTS
        // =================================================

        if (details.getDocuments() != null
                && !details
                        .getDocuments()
                        .isEmpty()) {

            documents.addAll(
                    details.getDocuments()
            );
        }


        // =================================================
        // 2. CHECK NESTED JOB PROFILE DOCUMENTS
        // =================================================

        if (documents.isEmpty()
                && details.getJobProfile() != null
                && details
                        .getJobProfile()
                        .getDocuments() != null
                && !details
                        .getJobProfile()
                        .getDocuments()
                        .isEmpty()) {

            documents.addAll(
                    details
                            .getJobProfile()
                            .getDocuments()
            );
        }


        // =================================================
        // 3. NO DOCUMENTS
        // =================================================

        if (documents.isEmpty()) {

            return false;
        }


        // =================================================
        // 4. SAVE DOCUMENTS
        // =================================================

        saveDocuments(
                jobProfile,
                documents
        );


        return true;
    }


    // =====================================================
    // SAVE DOCUMENTS
    // =====================================================

    private void saveDocuments(

            JobProfile jobProfile,

            List<SupersetJobProfileDetailsDto.DocumentDto>
                    documents

    ) {

        if (documents == null
                || documents.isEmpty()) {

            return;
        }


        for (
                SupersetJobProfileDetailsDto.DocumentDto document
                : documents
        ) {

            if (document.getIdentifier() == null
                    || document
                            .getIdentifier()
                            .isBlank()) {

                continue;
            }


            boolean exists =
                    jobDocumentRepository

                            .findByJobProfileIdAndSupersetDocumentId(

                                    jobProfile.getId(),

                                    document.getIdentifier()

                            )

                            .isPresent();


            if (exists) {

                continue;
            }


            JobDocument jobDocument =
                    new JobDocument();


            jobDocument.setJobProfile(
                    jobProfile
            );


            jobDocument.setSupersetDocumentId(
                    document.getIdentifier()
            );


            jobDocument.setName(
                    document.getName()
            );


            jobDocument.setType(
                    document.getType()
            );


            jobDocument.setContentType(
                    document.getContentType()
            );


            jobDocumentRepository.save(
                    jobDocument
            );
        }
    }


    // =====================================================
    // DOWNLOAD PDF + EXTRACT TEXT
    // =====================================================

    private String extractJobDescriptionText(

            String studentId,

            String supersetJobProfileId,

            String documentId,

            String authorization

    ) {

        // =================================================
        // GET DOCUMENT URL
        // =================================================

        String documentUrl =
                supersetClient.getDocumentUrl(

                        studentId,

                        supersetJobProfileId,

                        documentId,

                        authorization
                );


        if (documentUrl == null
                || documentUrl.isBlank()) {

            throw new RuntimeException(
                    "Unable to retrieve document URL from Superset"
            );
        }


        // =================================================
        // DOWNLOAD PDF
        // =================================================

        byte[] pdfBytes =
                supersetClient.downloadDocument(
                        documentUrl
                );


        if (pdfBytes == null
                || pdfBytes.length == 0) {

            throw new RuntimeException(
                    "Downloaded document is empty"
            );
        }


        // =================================================
        // EXTRACT TEXT
        // =================================================

        String text =
                pdfTextExtractionService.extractText(
                        pdfBytes
                );


        if (text == null
                || text.isBlank()) {

            throw new RuntimeException(
                    "Unable to extract text from job description"
            );
        }


        return text;
    }


    // =====================================================
    // CREATE GEMINI PROMPT
    // =====================================================

    private String analyzeJobDescription(

            String jobDescription

    ) {

        String prompt = """

                Analyze the following job information for a
                college placement student.

                Return ONLY valid JSON.

                Do not use markdown.

                Do not use ```json.

                Do not add any text before or after the JSON.

                Use exactly this structure:

                {
                  "summary": "short summary of the role",
                  "requiredSkills": "comma-separated technical skills",
                  "oaTopics": "comma-separated topics likely for online assessment",
                  "oaDifficulty": "Easy, Medium, or Hard",
                  "preparationTopics": "comma-separated preparation topics",
                  "analysis": "complete detailed analysis and recommendations"
                }

                IMPORTANT:

                - If a complete official job description is
                  provided, base the analysis primarily on it.

                - If the official job description PDF is not
                  available, use the company name, job role,
                  location, position type, available job
                  description information, and compensation
                  information provided.

                - When the information is incomplete, do not
                  invent company-specific requirements.

                - Clearly distinguish likely preparation areas
                  from confirmed requirements.

                - Tailor the analysis for a college placement
                  student.

                Job Information:

                %s

                """.formatted(
                jobDescription
        );


        return geminiService.generateAnalysis(
                prompt
        );
    }


    // =====================================================
    // GEMINI JSON -> DTO
    // =====================================================

    private GeminiJobAnalysisDto analyzeJobDescriptionAsDto(

            String jobDescription

    ) {

        String json =
                analyzeJobDescription(
                        jobDescription
                );


        try {

            return objectMapper.readValue(

                    json,

                    GeminiJobAnalysisDto.class

            );

        } catch (Exception e) {

            throw new RuntimeException(

                    "Failed to parse Gemini response: "
                            + json,

                    e

            );
        }
    }
}