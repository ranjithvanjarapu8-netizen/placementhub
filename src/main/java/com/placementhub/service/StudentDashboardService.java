package com.placementhub.service;

import com.placementhub.dto.StudentDashboardDto;
import com.placementhub.entity.StudentJob;
import com.placementhub.repository.StudentJobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDashboardService {

    private final StudentJobRepository studentJobRepository;

    public StudentDashboardService(
            StudentJobRepository studentJobRepository
    ) {
        this.studentJobRepository = studentJobRepository;
    }

    public StudentDashboardDto getDashboard(
            Long studentId
    ) {

        List<StudentJob> jobs =
                studentJobRepository.findByStudentId(studentId);

        long appliedJobs =
                jobs.stream()
                        .filter(job ->
                                "APPLIED".equalsIgnoreCase(
                                        job.getApplicationStatus()
                                )
                        )
                        .count();

        long yetToApplyJobs =
                jobs.stream()
                        .filter(job ->
                                "YET_TO_APPLY".equalsIgnoreCase(
                                        job.getApplicationStatus()
                                )
                                &&
                                job.getJobProfile() != null
                                &&
                                "OPEN_FOR_APPLICATIONS".equalsIgnoreCase(
                                        job.getJobProfile().getStatus()
                                )
                        )
                        .count();

        StudentDashboardDto dto =
                new StudentDashboardDto();

        dto.setAppliedJobs(appliedJobs);

        dto.setYetToApplyJobs(yetToApplyJobs);

        dto.setOpenJobs(
                yetToApplyJobs
        );

        return dto;
    }
}