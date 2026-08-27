package com.placementhub.controller;

import com.placementhub.dto.SupersetJobProfileDto;
import com.placementhub.service.SupersetClient;
import com.placementhub.service.SupersetSession;
import com.placementhub.service.SupersetSyncService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/sync")
public class SupersetSyncController {

    private final SupersetClient supersetClient;

    private final SupersetSyncService syncService;

    public SupersetSyncController(
            SupersetClient supersetClient,
            SupersetSyncService syncService
    ) {
        this.supersetClient = supersetClient;
        this.syncService = syncService;
    }


    // ============================================================
    // SYNC JOB PROFILES FOR LOGGED-IN STUDENT
    // ============================================================

    @PostMapping("/superset")
    public String sync(
            HttpSession httpSession
    ) {

        // ========================================================
        // 1. GET SUPERSET SESSION
        // ========================================================

        SupersetSession session =
                (SupersetSession) httpSession.getAttribute(
                        "SUPERSET_SESSION"
                );


        // ========================================================
        // 2. CHECK WHETHER STUDENT IS LOGGED IN
        // ========================================================

        if (session == null) {

            return "Please login with Superset first";
        }


        // ========================================================
        // 3. GET STUDENT ID
        // ========================================================

        String studentId =
                session.getUuid();

        // ========================================================
        // 4. GET SUPERSET SESSION KEY
        // ========================================================

        String authorization =
                session.getSessionKey();


        // ========================================================
        // 5. GET LATEST JOB PROFILES FROM SUPERSET
        // ========================================================

        List<SupersetJobProfileDto> jobs =
                supersetClient.getJobProfiles(
                        studentId,
                        authorization
                );


        // ========================================================
        // 6. SYNCHRONIZE JOB PROFILES WITH DATABASE
        // ========================================================

        int count =
                syncService.syncJobProfiles(
                        jobs,
                        studentId,
                        authorization
                );


        // ========================================================
        // 7. RETURN RESULT
        // ========================================================

        return "Synchronized "
                + count
                + " job profiles";
    }
}