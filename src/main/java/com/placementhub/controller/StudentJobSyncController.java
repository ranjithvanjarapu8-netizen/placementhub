package com.placementhub.controller;

import com.placementhub.dto.SupersetJobProfileDto;

import com.placementhub.service.StudentJobSyncService;
import com.placementhub.service.SupersetClient;
import com.placementhub.service.SupersetSyncService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/student-job-sync")
public class StudentJobSyncController {

    private final SupersetClient supersetClient;

    private final SupersetSyncService supersetSyncService;

    private final StudentJobSyncService studentJobSyncService;

    public StudentJobSyncController(
            SupersetClient supersetClient,
            SupersetSyncService supersetSyncService,
            StudentJobSyncService studentJobSyncService
    ) {

        this.supersetClient = supersetClient;

        this.supersetSyncService = supersetSyncService;

        this.studentJobSyncService = studentJobSyncService;
    }

    @PostMapping
    public String syncStudentJobs(

            @RequestParam String studentId,

            @RequestHeader("Authorization") String authorization

    ) {

        // =====================================================
        // 1. GET STUDENT'S JOBS FROM SUPERSET
        // =====================================================

        List<SupersetJobProfileDto> jobs =
                supersetClient.getJobProfiles(
                        studentId,
                        authorization
                );


        // =====================================================
        // 2. CREATE / UPDATE JOB PROFILES
        // =====================================================

        int jobProfileCount =
                supersetSyncService.syncJobProfiles(
                        jobs,
                        studentId,
                        authorization
                );


        // =====================================================
        // 3. CREATE / UPDATE STUDENT JOBS
        // =====================================================

        int studentJobCount =
                studentJobSyncService.syncStudentJobs(
                        studentId,
                        jobs
                );


        // =====================================================
        // 4. RESPONSE
        // =====================================================

        return "Job profiles synchronized: "
                + jobProfileCount
                + ", Student jobs synchronized: "
                + studentJobCount;
    }
}