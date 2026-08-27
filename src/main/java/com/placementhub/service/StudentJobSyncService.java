package com.placementhub.service;

import com.placementhub.dto.SupersetJobProfileDto;
import com.placementhub.entity.JobProfile;
import com.placementhub.entity.Student;
import com.placementhub.entity.StudentJob;
import com.placementhub.repository.JobProfileRepository;
import com.placementhub.repository.StudentJobRepository;
import com.placementhub.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class StudentJobSyncService {

    private final StudentRepository studentRepository;

    private final JobProfileRepository jobProfileRepository;

    private final StudentJobRepository studentJobRepository;

    public StudentJobSyncService(
            StudentRepository studentRepository,
            JobProfileRepository jobProfileRepository,
            StudentJobRepository studentJobRepository
    ) {

        this.studentRepository = studentRepository;

        this.jobProfileRepository = jobProfileRepository;

        this.studentJobRepository = studentJobRepository;
    }

    @Transactional
    public int syncStudentJobs(
            String supersetStudentId,
            List<SupersetJobProfileDto> jobs
    ) {

        // =====================================================
        // 1. FIND LOCAL STUDENT
        // =====================================================

        Student student = studentRepository
                .findBySupersetStudentId(supersetStudentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found for Superset ID: "
                                        + supersetStudentId
                        )
                );

        int count = 0;

        // =====================================================
        // 2. PROCESS ALL STUDENT JOB APPLICATIONS
        // =====================================================

        for (SupersetJobProfileDto dto : jobs) {

            // =================================================
            // 3. FIND JOB PROFILE
            // =================================================

            JobProfile jobProfile = jobProfileRepository
                    .findBySupersetJobProfileId(
                            dto.getJobProfileIdentifier()
                    )
                    .orElse(null);

            /*
             * SupersetSyncService should have already created
             * the JobProfile before this service is called.
             *
             * If it is still missing, skip it.
             */

            if (jobProfile == null) {
                continue;
            }

            // =================================================
            // 4. FIND OR CREATE STUDENT-JOB
            // =================================================

            StudentJob studentJob = studentJobRepository
                    .findByStudentIdAndJobProfileId(
                            student.getId(),
                            jobProfile.getId()
                    )
                    .orElseGet(StudentJob::new);

            studentJob.setStudent(student);

            studentJob.setJobProfile(jobProfile);

            // =================================================
            // 5. APPLICATION DATA
            // =================================================

            studentJob.setApplicationStatus(
                    dto.getJobApplicationStatus()
            );

            studentJob.setCurrentStage(
                    dto.getJobApplicationCurrentStage()
            );

            studentJob.setRankByStudent(
                    dto.getRankByStudent()
            );

            // =================================================
            // 6. ELIGIBILITY DATA
            // =================================================

            studentJob.setHasEligibilityDataChangedAfterApplying(
                    dto.getHasEligibilityDataChangedAfterApplying()
            );

            studentJob.setIsEligibleAfterDataChange(
                    dto.getIsEligibleAfterDataChange()
            );

            studentJob.setIneligibleBasedOnCustomQuestions(
                    dto.getIneligibleBasedOnCustomQuestions()
            );

            studentJob.setRejectedDueToEligibilityDataChange(
                    dto.getRejectedDueToEligibilityDataChange()
            );

            studentJob.setEligibilityHistoryAvailable(
                    dto.getEligibilityHistoryAvailable()
            );

            // =================================================
            // 7. APPLICATION DATE
            // =================================================

            if (dto.getJobApplicationLastAppliedAt() != null) {

                studentJob.setAppliedAt(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(
                                        dto.getJobApplicationLastAppliedAt()
                                ),
                                ZoneId.systemDefault()
                        )
                );

            } else {

                studentJob.setAppliedAt(null);
            }

            // =================================================
            // 8. LAST SYNC TIME
            // =================================================

            studentJob.setLastSyncedAt(
                    LocalDateTime.now()
            );

            // =================================================
            // 9. SAVE
            // =================================================

            studentJobRepository.save(studentJob);

            count++;
        }

        return count;
    }
}