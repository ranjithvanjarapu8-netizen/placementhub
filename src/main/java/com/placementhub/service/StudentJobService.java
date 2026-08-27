package com.placementhub.service;

import com.placementhub.dto.StudentJobResponseDto;
import com.placementhub.entity.Student;
import com.placementhub.entity.StudentJob;
import com.placementhub.repository.StudentJobRepository;
import com.placementhub.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentJobService {

    private final StudentJobRepository studentJobRepository;

    private final StudentRepository studentRepository;


    public StudentJobService(
            StudentJobRepository studentJobRepository,
            StudentRepository studentRepository
    ) {

        this.studentJobRepository = studentJobRepository;

        this.studentRepository = studentRepository;
    }


    // =====================================================
    // GET LOGGED-IN STUDENT JOBS
    // =====================================================

    public List<StudentJobResponseDto> getStudentJobs(

            String supersetStudentId,

            String status,

            String sort

    ) {

        // =================================================
        // 1. FIND LOCAL STUDENT USING SUPERSET UUID
        // =================================================

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


        // =================================================
        // 2. GET LOCAL STUDENT ID
        // =================================================

        Long studentId =
                student.getId();


        // =================================================
        // 3. GET STUDENT JOBS
        // =================================================

        List<StudentJob> jobs =
                studentJobRepository.findByStudentId(
                        studentId
                );


        // =================================================
        // 4. FILTER BY APPLICATION STATUS
        // =================================================

        if (status != null && !status.isBlank()) {

            if (status.equalsIgnoreCase(
                    "YET_TO_APPLY"
            )) {

                jobs = jobs.stream()
                        .filter(
                                job ->
                                        "YET_TO_APPLY"
                                                .equalsIgnoreCase(
                                                        job.getApplicationStatus()
                                                )
                                        &&
                                        job.getJobProfile() != null
                                        &&
                                        "OPEN_FOR_APPLICATIONS"
                                                .equalsIgnoreCase(
                                                        job.getJobProfile()
                                                                .getStatus()
                                                )
                        )
                        .toList();

            } else {

                jobs = jobs.stream()
                        .filter(
                                job ->
                                        status.equalsIgnoreCase(
                                                job.getApplicationStatus()
                                        )
                        )
                        .toList();
            }
        }


        // =================================================
        // 5. SORT
        // =================================================

        if (sort != null && !sort.isBlank()) {

            switch (sort.toUpperCase()) {

                // =========================================
                // NEWEST
                // =========================================

                case "NEWEST":

                    jobs = jobs.stream()
                            .sorted(
                                    Comparator.comparing(
                                            job ->
                                                    job.getJobProfile()
                                                            .getSupersetCreatedAt(),

                                            Comparator.nullsLast(
                                                    Comparator.reverseOrder()
                                            )
                                    )
                            )
                            .toList();

                    break;


                // =========================================
                // HIGHEST CTC
                // =========================================

                case "CTC":

                    jobs = jobs.stream()
                            .sorted(
                                    Comparator.comparing(
                                            job ->
                                                    job.getJobProfile()
                                                            .getEffectiveCTC(),

                                            Comparator.nullsLast(
                                                    Comparator.reverseOrder()
                                            )
                                    )
                            )
                            .toList();

                    break;


                // =========================================
                // EARLIEST DEADLINE
                // =========================================

                case "DEADLINE":

                    jobs = jobs.stream()
                            .sorted(
                                    Comparator.comparing(
                                            job ->
                                                    job.getJobProfile()
                                                            .getApplicationDeadline(),

                                            Comparator.nullsLast(
                                                    Comparator.naturalOrder()
                                            )
                                    )
                            )
                            .toList();

                    break;


                // =========================================
                // UNKNOWN SORT
                // =========================================

                default:

                    // Keep original database order.

                    break;
            }
        }


        // =================================================
        // 6. CONVERT TO DTO
        // =================================================

        return jobs.stream()
                .map(this::convertToDto)
                .toList();
    }


    // =====================================================
    // CONVERT ENTITY TO DTO
    // =====================================================

    private StudentJobResponseDto convertToDto(
            StudentJob studentJob
    ) {

        StudentJobResponseDto dto =
                new StudentJobResponseDto();


        // =================================================
        // STUDENT-JOB INFORMATION
        // =================================================

        dto.setStudentJobId(
                studentJob.getId()
        );


        dto.setApplicationStatus(
                studentJob.getApplicationStatus()
        );


        dto.setAppliedAt(
                studentJob.getAppliedAt()
        );


        dto.setCurrentStage(
                studentJob.getCurrentStage()
        );


        dto.setRankByStudent(
                studentJob.getRankByStudent()
        );


        // =================================================
        // JOB INFORMATION
        // =================================================

        if (studentJob.getJobProfile() != null) {

            var jobProfile =
                    studentJob.getJobProfile();


            dto.setJobId(
                    jobProfile.getId()
            );


            dto.setApplicationDeadline(
                    jobProfile.getApplicationDeadline()
            );


            dto.setSupersetCreatedAt(
                    jobProfile.getSupersetCreatedAt()
            );


            dto.setTitle(
                    jobProfile.getTitle()
            );


            dto.setLocation(
                    jobProfile.getLocation()
            );


            dto.setPositionType(
                    jobProfile.getPositionType()
            );


            dto.setJobStatus(
                    jobProfile.getStatus()
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


            // =================================================
            // COMPANY
            // =================================================

            if (jobProfile.getCompany() != null) {

                dto.setCompanyName(
                        jobProfile.getCompany().getName()
                );


                dto.setCompanyLogoUuid(
                        jobProfile.getCompany().getLogoUuid()
                );
            }
        }


        return dto;
    }
}