package com.placementhub.controller;

import com.placementhub.dto.SupersetStudentDto;
import com.placementhub.entity.Student;
import com.placementhub.service.StudentSyncService;
import com.placementhub.service.SupersetClient;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/student-sync")
public class StudentSyncController {

    private final SupersetClient supersetClient;
    private final StudentSyncService studentSyncService;

    public StudentSyncController(
            SupersetClient supersetClient,
            StudentSyncService studentSyncService
    ) {
        this.supersetClient = supersetClient;
        this.studentSyncService = studentSyncService;
    }

    @PostMapping
    public String syncStudent(
            @RequestParam String studentId,
            @RequestHeader("Authorization") String authorization
    ) {

        // 1. Get student from Superset
        SupersetStudentDto dto =
                supersetClient.getStudent(
                        studentId,
                        authorization
                );

        // 2. Save/update student in our database
        Student student =
                studentSyncService.syncStudent(dto);

        return "Student synchronized successfully. Local student ID: "
                + student.getId();
    }
}