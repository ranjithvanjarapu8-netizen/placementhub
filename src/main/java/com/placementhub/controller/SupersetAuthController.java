package com.placementhub.controller;

import com.placementhub.dto.SupersetLoginRequest;
import com.placementhub.dto.SupersetLoginResponse;
import com.placementhub.entity.SupersetStudentSession;
import com.placementhub.repository.SupersetStudentSessionRepository;
import com.placementhub.service.SupersetClient;
import com.placementhub.service.SupersetSession;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class SupersetAuthController {

    private final SupersetClient supersetClient;

    private final SupersetStudentSessionRepository sessionRepository;


    public SupersetAuthController(
            SupersetClient supersetClient,
            SupersetStudentSessionRepository sessionRepository
    ) {

        this.supersetClient = supersetClient;

        this.sessionRepository = sessionRepository;
    }


    // ============================================================
    // SUPERSET LOGIN
    // ============================================================

    @PostMapping("/superset-login")
    public SupersetLoginResponse login(

            @RequestBody SupersetLoginRequest request,

            HttpSession httpSession

    ) {

        // ========================================================
        // 1. LOGIN TO SUPERSET
        // ========================================================

        SupersetLoginResponse response =
                supersetClient.login(
                        request.getUsername(),
                        request.getPassword()
                );


        // ========================================================
        // 2. CREATE OUR SESSION OBJECT
        // ========================================================

        SupersetSession supersetSession =
                new SupersetSession(

                        response.getUserId(),

                        response.getUsername(),

                        response.getName(),

                        response.getUuid(),

                        response.getSessionKey()
                );


        // ========================================================
        // 3. STORE IN HTTP SESSION
        // ========================================================

        httpSession.setAttribute(
                "SUPERSET_SESSION",
                supersetSession
        );


        // ========================================================
        // 4. FIND EXISTING DATABASE SESSION
        // ========================================================

        SupersetStudentSession dbSession =
                sessionRepository
                        .findByStudentUuid(
                                response.getUuid()
                        )
                        .orElseGet(
                                SupersetStudentSession::new
                        );


        // ========================================================
        // 5. UPDATE DATABASE SESSION
        // ========================================================

        dbSession.setSupersetUserId(
                response.getUserId()
        );

        dbSession.setStudentUuid(
                response.getUuid()
        );

        dbSession.setUsername(
                response.getUsername()
        );

        dbSession.setName(
                response.getName()
        );

        dbSession.setSessionKey(
                response.getSessionKey()
        );

        dbSession.setLastLoginAt(
                LocalDateTime.now()
        );


        // ========================================================
        // 6. SAVE
        // ========================================================

        SupersetStudentSession savedSession =
                sessionRepository.save(dbSession);

        System.out.println(
                "SUPSERSET SESSION SAVED: ID = "
                        + savedSession.getId()
                        + ", UUID = "
                        + savedSession.getStudentUuid()
        );


        // ========================================================
        // 7. RETURN SUPERSET RESPONSE
        // ========================================================

        return response;
    }
}