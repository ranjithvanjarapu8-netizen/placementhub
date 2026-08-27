package com.placementhub.controller;

import com.placementhub.dto.StudentDashboardDto;
import com.placementhub.service.StudentDashboardService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentDashboardController {

    private final StudentDashboardService studentDashboardService;

    public StudentDashboardController(
            StudentDashboardService studentDashboardService
    ) {
        this.studentDashboardService = studentDashboardService;
    }

    // =====================================================
    // GET STUDENT DASHBOARD
    // =====================================================

    @GetMapping("/{studentId}/dashboard")
    public StudentDashboardDto getDashboard(
            @PathVariable Long studentId
    ) {

        return studentDashboardService.getDashboard(
                studentId
        );
    }
}