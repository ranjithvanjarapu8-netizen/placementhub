package com.placementhub.controller;

import com.placementhub.dto.JobProfileDetailsDto;
import com.placementhub.dto.JobProfileResponseDto;
import com.placementhub.service.JobProfileService;
import com.placementhub.service.SupersetSession;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobProfileController {

    private final JobProfileService jobProfileService;

    public JobProfileController(
            JobProfileService jobProfileService
    ) {
        this.jobProfileService = jobProfileService;
    }

    // =====================================================
    // GET ALL / SEARCH / FILTER JOBS
    // =====================================================

    @GetMapping
    public List<JobProfileResponseDto> getJobs(

            @RequestParam(required = false)
            String search,

            @RequestParam(required = false)
            String location,

            @RequestParam(required = false)
            String positionType,

            @RequestParam(required = false)
            Long minCtc,

            @RequestParam(required = false)
            Long maxCtc,

            @RequestParam(required = false)
            String sort
    ) {

        // No filters → return all jobs
        if (
                search == null &&
                location == null &&
                positionType == null &&
                minCtc == null &&
                maxCtc == null &&
                sort == null
        ) {

            return jobProfileService.getAllJobs();
        }

        // Filters/search/sorting provided
        return jobProfileService.searchJobs(
                search,
                location,
                positionType,
                minCtc,
                maxCtc,
                sort
        );
    }

    // =====================================================
    // GET JOB DETAILS
    // =====================================================

    @GetMapping("/{id}")
    public JobProfileDetailsDto getJobById(
            @PathVariable Long id,
            HttpSession httpSession
    ) {

        SupersetSession session =
                (SupersetSession) httpSession.getAttribute(
                        "SUPERSET_SESSION"
                );

        if (session == null) {
            throw new RuntimeException(
                    "Please login with Superset first"
            );
        }

        String supersetStudentId =
                session.getUuid();

        return jobProfileService.getJobBySupersetStudent(
                id,
                supersetStudentId
        );
    }
}