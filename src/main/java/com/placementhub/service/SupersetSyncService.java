package com.placementhub.service;

import com.placementhub.dto.SupersetJobProfileDto;

import com.placementhub.entity.Company;
import com.placementhub.entity.JobProfile;

import com.placementhub.repository.CompanyRepository;
import com.placementhub.repository.JobProfileRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupersetSyncService {

    private final CompanyRepository companyRepository;

    private final JobProfileRepository jobProfileRepository;


    public SupersetSyncService(
            CompanyRepository companyRepository,
            JobProfileRepository jobProfileRepository
    ) {

        this.companyRepository = companyRepository;

        this.jobProfileRepository = jobProfileRepository;
    }


    // ============================================================
    // SYNC ALL JOB PROFILES
    // ============================================================

    @Transactional
    public int syncJobProfiles(

            List<SupersetJobProfileDto> jobs,

            String studentId,

            String authorization

    ) {

        if (jobs == null || jobs.isEmpty()) {

            return 0;
        }


        int count = 0;


        for (SupersetJobProfileDto dto : jobs) {

            try {

                // =================================================
                // 1. FIND OR CREATE COMPANY
                // =================================================

                Company company =
                        companyRepository
                                .findBySupersetCompanyId(
                                        dto.getCompanyCode()
                                )
                                .orElseGet(Company::new);


                company.setSupersetCompanyId(
                        dto.getCompanyCode()
                );


                company.setName(
                        dto.getCompanyName()
                );


                company.setLogoUuid(
                        dto.getCompanyLogoUuid()
                );


                company =
                        companyRepository.save(company);


                // =================================================
                // 2. FIND OR CREATE JOB PROFILE
                // =================================================

                JobProfile jobProfile =
                        jobProfileRepository
                                .findBySupersetJobProfileId(
                                        dto.getJobProfileIdentifier()
                                )
                                .orElseGet(JobProfile::new);


                // =================================================
                // 3. BASIC JOB INFORMATION
                // =================================================

                jobProfile.setSupersetJobProfileId(
                        dto.getJobProfileIdentifier()
                );


                jobProfile.setCompany(
                        company
                );


                jobProfile.setTitle(
                        dto.getJobProfileTitle()
                );


                jobProfile.setLocation(
                        dto.getJobProfileLocation()
                );


                jobProfile.setPositionType(
                        dto.getPositionType()
                );


                // =================================================
                // 4. FIELDS THAT CAN CHANGE
                // =================================================

                jobProfile.setApplicationDeadline(
                        dto.getJobProfileApplicationDeadline()
                );


                jobProfile.setStatus(
                        dto.getJobProfileStatus()
                );


                jobProfile.setCurrentStage(
                        dto.getJobProfileCurrentStage()
                );


                // =================================================
                // 5. PLACEMENT INFORMATION
                // =================================================

                jobProfile.setPlacementName(
                        dto.getPlacementName()
                );


                jobProfile.setPlacementUuid(
                        dto.getPlacementUUID()
                );


                jobProfile.setPlacementCategoryName(
                        dto.getPlacementCategoryName()
                );


                jobProfile.setPlacementCategoryUuid(
                        dto.getPlacementCategoryUuid()
                );


                jobProfile.setPlacementCategoryLevel(
                        dto.getPlacementCategoryLevel()
                );


                jobProfile.setCompanyLogoUuid(
                        dto.getCompanyLogoUuid()
                );


                jobProfile.setPpo(
                        dto.getPpo()
                );


                jobProfile.setSupersetCreatedAt(
                        dto.getCreatedAt()
                );


                // =================================================
                // 6. UPDATE SYNC TIME
                // =================================================

                jobProfile.setLastSyncedAt(
                        LocalDateTime.now()
                );


                // =================================================
                // 7. SAVE JOB PROFILE
                // =================================================

                jobProfileRepository.save(
                        jobProfile
                );


                count++;


            } catch (Exception e) {

                /*
                 * One job failing should not stop
                 * synchronization of remaining jobs.
                 */

                System.err.println(
                        "Failed to sync job: "
                                + dto.getJobProfileIdentifier()
                                + " : "
                                + e.getMessage()
                );
            }
        }


        return count;
    }
}