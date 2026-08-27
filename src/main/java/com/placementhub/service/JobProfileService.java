package com.placementhub.service;

import com.placementhub.dto.JobProfileDetailsDto;
import com.placementhub.dto.JobProfileResponseDto;
import com.placementhub.entity.JobProfile;
import com.placementhub.entity.Student;
import com.placementhub.repository.JobProfileRepository;
import com.placementhub.repository.StudentJobRepository;
import com.placementhub.repository.StudentRepository;

import java.util.Comparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProfileService {

	private final JobProfileRepository jobProfileRepository;
	private final StudentJobRepository studentJobRepository;
	private final StudentRepository studentRepository;
	public JobProfileService(
	        JobProfileRepository jobProfileRepository,
	        StudentJobRepository studentJobRepository,
	        StudentRepository studentRepository
	) {
	    this.jobProfileRepository = jobProfileRepository;
	    this.studentJobRepository = studentJobRepository;
	    this.studentRepository = studentRepository;
	}

    // =====================================================
    // GET ALL JOBS
    // =====================================================

    public List<JobProfileResponseDto> getAllJobs() {

        return jobProfileRepository
                .findAll()
                .stream()
                .filter(job ->
                        "OPEN_FOR_APPLICATIONS".equalsIgnoreCase(
                                job.getStatus()
                        )
                )
                .map(this::convertToListDto)
                .toList();
    }
 // =====================================================
 // SEARCH AND FILTER JOBS
 // =====================================================

 public List<JobProfileResponseDto> searchJobs(
         String search,
         String location,
         String positionType,
         Long minCtc,
         Long maxCtc,
         String sort
 ) {

     List<JobProfile> jobs =
             jobProfileRepository.findAll();
     
     jobs = jobs.stream()
             .filter(job ->
                     "OPEN_FOR_APPLICATIONS".equalsIgnoreCase(
                             job.getStatus()
                     )
             )
             .toList();


     // =================================================
     // 1. SEARCH
     // =================================================

     if (search != null && !search.isBlank()) {

         String keyword =
                 search.trim().toLowerCase();

         jobs = jobs.stream()
                 .filter(job ->

                         (job.getTitle() != null &&
                                 job.getTitle()
                                         .toLowerCase()
                                         .contains(keyword))

                         ||

                         (job.getCompany() != null &&
                                 job.getCompany()
                                         .getName()
                                         .toLowerCase()
                                         .contains(keyword))

                         ||

                         (job.getLocation() != null &&
                                 job.getLocation()
                                         .toLowerCase()
                                         .contains(keyword))
                 )
                 .toList();
     }

     // =================================================
     // 2. LOCATION FILTER
     // =================================================

     if (location != null && !location.isBlank()) {

         String locationKeyword =
                 location.trim().toLowerCase();

         jobs = jobs.stream()
                 .filter(job ->
                         job.getLocation() != null &&
                         job.getLocation()
                                 .toLowerCase()
                                 .contains(locationKeyword)
                 )
                 .toList();
     }

     // =================================================
     // 3. POSITION TYPE FILTER
     // =================================================

     if (positionType != null && !positionType.isBlank()) {

         jobs = jobs.stream()
                 .filter(job ->
                         job.getPositionType() != null &&
                         job.getPositionType()
                                 .equalsIgnoreCase(positionType)
                 )
                 .toList();
     }

     // =================================================
     // 4. MINIMUM CTC
     // =================================================

     if (minCtc != null) {

         jobs = jobs.stream()
                 .filter(job ->
                         job.getEffectiveCTC() != null &&
                         job.getEffectiveCTC() >= minCtc
                 )
                 .toList();
     }

     // =================================================
     // 5. MAXIMUM CTC
     // =================================================

     if (maxCtc != null) {

         jobs = jobs.stream()
                 .filter(job ->
                         job.getEffectiveCTC() != null &&
                         job.getEffectiveCTC() <= maxCtc
                 )
                 .toList();
     }

     // =================================================
     // 6. SORTING
     // =================================================

     if (sort != null && !sort.isBlank()) {

         switch (sort.toUpperCase()) {

             case "NEWEST":

                 jobs = jobs.stream()
                         .sorted(
                                 Comparator.comparing(
                                         JobProfile::getSupersetCreatedAt,
                                         Comparator.nullsLast(
                                                 Comparator.reverseOrder()
                                         )
                                 )
                         )
                         .toList();

                 break;

             case "CTC":

                 jobs = jobs.stream()
                         .sorted(
                                 Comparator.comparing(
                                         JobProfile::getEffectiveCTC,
                                         Comparator.nullsLast(
                                                 Comparator.reverseOrder()
                                         )
                                 )
                         )
                         .toList();

                 break;
             case "OPEN_FIRST":

            	    jobs = jobs.stream()
            	            .sorted(
            	                    Comparator
            	                            .comparing(
            	                                    (JobProfile job) ->
            	                                            !"OPEN_FOR_APPLICATIONS"
            	                                                    .equalsIgnoreCase(
            	                                                            job.getStatus()
            	                                                    )
            	                            )
            	                            .thenComparing(
            	                                    JobProfile::getApplicationDeadline,
            	                                    Comparator.nullsLast(
            	                                            Comparator.naturalOrder()
            	                                    )
            	                            )
            	                            .thenComparing(
            	                                    JobProfile::getEffectiveCTC,
            	                                    Comparator.nullsLast(
            	                                            Comparator.reverseOrder()
            	                                    )
            	                            )
            	                            .thenComparing(
            	                                    JobProfile::getSupersetCreatedAt,
            	                                    Comparator.nullsLast(
            	                                            Comparator.reverseOrder()
            	                                    )
            	                            )
            	            )
            	            .toList();

            	    break;

             case "DEADLINE":

                 jobs = jobs.stream()
                         .sorted(
                                 Comparator.comparing(
                                         JobProfile::getApplicationDeadline,
                                         Comparator.nullsLast(
                                                 Comparator.naturalOrder()
                                         )
                                 )
                         )
                         .toList();

                 break;

             default:

                 break;
         }
     }

     // =================================================
     // 7. CONVERT TO DTO
     // =================================================

     return jobs.stream()
             .map(this::convertToListDto)
             .toList();
 }

    // =====================================================
    // GET JOB BY ID
    // =====================================================

	 public JobProfileDetailsDto getJobById(
		        Long id,
		        Long studentId
		) {
	
		    JobProfile jobProfile = jobProfileRepository
		            .findById(id)
		            .orElseThrow(() ->
		                    new RuntimeException(
		                            "Job profile not found with id: " + id
		                    )
		            );
	
		    JobProfileDetailsDto dto =
		            convertToDetailsDto(jobProfile);

		    studentJobRepository
		            .findByStudentIdAndJobProfileId(
		                    studentId,
		                    jobProfile.getId()
		            )
		            .ifPresent(studentJob ->
		                    dto.setApplicationStatus(
		                            studentJob.getApplicationStatus()
		                    )
		            );

		    return dto;
		}

    // =====================================================
    // CONVERT TO LIST DTO
    // =====================================================

    private JobProfileResponseDto convertToListDto(
            JobProfile jobProfile
    ) {

        JobProfileResponseDto dto =
                new JobProfileResponseDto();

        dto.setId(
                jobProfile.getId()
        );

        dto.setSupersetJobProfileId(
                jobProfile.getSupersetJobProfileId()
        );

        dto.setTitle(
                jobProfile.getTitle()
        );

        if (jobProfile.getCompany() != null) {

            dto.setCompanyName(
                    jobProfile.getCompany().getName()
            );

            dto.setCompanyLogoUuid(
                    jobProfile.getCompany().getLogoUuid()
            );
        }

        dto.setLocation(
                jobProfile.getLocation()
        );

        dto.setPositionType(
                jobProfile.getPositionType()
        );

        dto.setApplicationDeadline(
                jobProfile.getApplicationDeadline()
        );
        dto.setSupersetCreatedAt(
                jobProfile.getSupersetCreatedAt()
        );
        dto.setStatus(
                jobProfile.getStatus()
        );

        dto.setPlacementName(
                jobProfile.getPlacementName()
        );

        dto.setPlacementCategoryName(
                jobProfile.getPlacementCategoryName()
        );

        dto.setCtcMin(
                jobProfile.getCtcMin()
        );

        dto.setCtcMax(
                jobProfile.getCtcMax()
        );

        dto.setCtcInterval(
                jobProfile.getCtcInterval()
        );

        dto.setCtcCurrency(
                jobProfile.getCtcCurrency()
        );

        dto.setEffectiveCTC(
                jobProfile.getEffectiveCTC()
        );

        dto.setPpo(
                jobProfile.getPpo()
        );

        return dto;
    }

    // =====================================================
    // CONVERT TO DETAILS DTO
    // =====================================================

    private JobProfileDetailsDto convertToDetailsDto(
            JobProfile jobProfile
    ) {

        JobProfileDetailsDto dto =
                new JobProfileDetailsDto();

        dto.setId(
                jobProfile.getId()
        );

        dto.setSupersetJobProfileId(
                jobProfile.getSupersetJobProfileId()
        );

        dto.setTitle(
                jobProfile.getTitle()
        );

        if (jobProfile.getCompany() != null) {

            dto.setCompanyName(
                    jobProfile.getCompany().getName()
            );

            dto.setCompanyLogoUuid(
                    jobProfile.getCompany().getLogoUuid()
            );
        }

        dto.setLocation(
                jobProfile.getLocation()
        );

        dto.setPositionType(
                jobProfile.getPositionType()
        );

        dto.setApplicationDeadline(
                jobProfile.getApplicationDeadline()
        );

        dto.setStatus(
                jobProfile.getStatus()
        );

        dto.setCurrentStage(
                jobProfile.getCurrentStage()
        );

        dto.setPlacementName(
                jobProfile.getPlacementName()
        );

        dto.setPlacementUuid(
                jobProfile.getPlacementUuid()
        );

        dto.setPlacementCategoryName(
                jobProfile.getPlacementCategoryName()
        );

        dto.setPlacementCategoryUuid(
                jobProfile.getPlacementCategoryUuid()
        );

        dto.setPlacementCategoryLevel(
                jobProfile.getPlacementCategoryLevel()
        );

        dto.setPpo(
                jobProfile.getPpo()
        );

        dto.setSupersetCreatedAt(
                jobProfile.getSupersetCreatedAt()
        );

        dto.setJobDescription(
                jobProfile.getJobDescription()
        );

        dto.setCtcMin(
                jobProfile.getCtcMin()
        );

        dto.setCtcMax(
                jobProfile.getCtcMax()
        );

        dto.setCtcInterval(
                jobProfile.getCtcInterval()
        );

        dto.setCtcCurrency(
                jobProfile.getCtcCurrency()
        );

        dto.setCtcAdditionalInfo(
                jobProfile.getCtcAdditionalInfo()
        );

        dto.setCtcEquityDescription(
                jobProfile.getCtcEquityDescription()
        );

        dto.setEffectiveCTC(
                jobProfile.getEffectiveCTC()
        );

        return dto;
    }
    public JobProfileDetailsDto getJobBySupersetStudent(
            Long jobProfileId,
            String supersetStudentId
    ) {

        JobProfile jobProfile =
                jobProfileRepository
                        .findById(jobProfileId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Job profile not found with id: "
                                                + jobProfileId
                                )
                        );

        JobProfileDetailsDto dto =
                convertToDetailsDto(jobProfile);

        Student student =
                studentRepository
                        .findBySupersetStudentId(
                                supersetStudentId
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Student not found for Superset ID: "
                                                + supersetStudentId
                                )
                        );

        studentJobRepository
                .findByStudentIdAndJobProfileId(
                        student.getId(),
                        jobProfile.getId()
                )
                .ifPresent(
                        studentJob ->
                                dto.setApplicationStatus(
                                        studentJob.getApplicationStatus()
                                )
                );

        return dto;
    }
}