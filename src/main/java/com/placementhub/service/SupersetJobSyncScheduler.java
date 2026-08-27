package com.placementhub.service;

import com.placementhub.dto.SupersetJobProfileDto;
import com.placementhub.entity.SupersetStudentSession;
import com.placementhub.repository.SupersetStudentSessionRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupersetJobSyncScheduler {

    private final SupersetStudentSessionRepository sessionRepository;

    private final SupersetClient supersetClient;

    private final SupersetSyncService syncService;


    public SupersetJobSyncScheduler(
            SupersetStudentSessionRepository sessionRepository,
            SupersetClient supersetClient,
            SupersetSyncService syncService
    ) {

        this.sessionRepository = sessionRepository;

        this.supersetClient = supersetClient;

        this.syncService = syncService;
    }


    // ============================================================
    // AUTOMATIC JOB SYNCHRONIZATION
    // Runs every 30 minutes
    // ============================================================

    @Scheduled(fixedRate = 10800000)
    @Transactional
    public void synchronizeJobs() {

        System.out.println(
                "Automatic Superset synchronization started..."
        );


        // ========================================================
        // 1. GET ALL LOGGED-IN STUDENT SESSIONS
        // ========================================================

        List<SupersetStudentSession> sessions =
                sessionRepository
                        .findAllBySessionKeyIsNotNull();


        if (sessions.isEmpty()) {

            System.out.println(
                    "No Superset student sessions found."
            );

            return;
        }


        // ========================================================
        // 2. PROCESS EACH STUDENT
        // ========================================================

        for (SupersetStudentSession session : sessions) {

            try {

                synchronizeStudent(session);

            } catch (Exception e) {

                /*
                 * One student's failure should not stop
                 * synchronization for other students.
                 */

                System.err.println(
                        "Failed to synchronize student "
                                + session.getStudentUuid()
                                + ": "
                                + e.getMessage()
                );
            }
        }


        System.out.println(
                "Automatic Superset synchronization completed."
        );
    }


    // ============================================================
    // SYNCHRONIZE ONE STUDENT
    // ============================================================

    private void synchronizeStudent(
            SupersetStudentSession session
    ) {

        String studentUuid =
                session.getStudentUuid();


        String sessionKey =
                session.getSessionKey();


        System.out.println(
                "Synchronizing jobs for student UUID: "
                        + studentUuid
        );


        // ========================================================
        // 1. GET LATEST JOB PROFILES FROM SUPERSET
        // ========================================================

        List<SupersetJobProfileDto> jobs =
                supersetClient.getJobProfiles(
                        studentUuid,
                        sessionKey
                );


        // ========================================================
        // 2. UPDATE DATABASE
        // ========================================================

        int count =
                syncService.syncJobProfiles(
                        jobs,
                        studentUuid,
                        sessionKey
                );


        // ========================================================
        // 3. UPDATE LAST SYNC TIME
        // ========================================================

        session.setLastSyncedAt(
                LocalDateTime.now()
        );


        sessionRepository.save(session);


        System.out.println(
                "Student "
                        + studentUuid
                        + " synchronized "
                        + count
                        + " job profiles."
        );
    }
}