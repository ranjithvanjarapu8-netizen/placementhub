package com.placementhub.controller;

import com.placementhub.dto.StudentJobResponseDto;
import com.placementhub.service.StudentJobService;
import com.placementhub.service.SupersetSession;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentJobController {

    private final StudentJobService studentJobService;

    public StudentJobController(
            StudentJobService studentJobService
    ) {
        this.studentJobService = studentJobService;
    }


    // =====================================================
    // GET LOGGED-IN STUDENT JOBS
    // =====================================================

    @GetMapping("/jobs")
    public List<StudentJobResponseDto> getStudentJobs(

            @RequestParam(required = false)
            String status,

            @RequestParam(required = false)
            String sort,

            HttpSession httpSession

    ) {

        // =================================================
        // 1. GET SUPERSET SESSION
        // =================================================

        SupersetSession session =
                (SupersetSession) httpSession.getAttribute(
                        "SUPERSET_SESSION"
                );


        // =================================================
        // 2. CHECK LOGIN
        // =================================================

        if (session == null) {

            throw new RuntimeException(
                    "Please login with Superset first"
            );
        }


        // =================================================
        // 3. GET SUPERSET STUDENT UUID
        // =================================================

        String supersetStudentId =
                session.getUuid();


        // =================================================
        // 4. GET STUDENT JOBS
        // =================================================

        return studentJobService.getStudentJobs(
                supersetStudentId,
                status,
                sort
        );
    }
}