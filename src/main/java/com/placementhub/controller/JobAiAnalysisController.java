package com.placementhub.controller;

import com.placementhub.dto.JobAiAnalysisDto;
import com.placementhub.service.JobAiAnalysisService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobAiAnalysisController {

    private final JobAiAnalysisService jobAiAnalysisService;

    public JobAiAnalysisController(
            JobAiAnalysisService jobAiAnalysisService
    ) {
        this.jobAiAnalysisService = jobAiAnalysisService;
    }

    // =====================================================
    // GET EXISTING AI ANALYSIS
    // =====================================================

    @GetMapping("/{jobProfileId}/ai-analysis")
    public JobAiAnalysisDto getAnalysis(
            @PathVariable Long jobProfileId
    ) {

        return jobAiAnalysisService.getAnalysis(jobProfileId);
    }


    // =====================================================
    // GET OR GENERATE AI ANALYSIS
    // =====================================================

    @PostMapping("/{jobProfileId}/ai-analysis")
    public JobAiAnalysisDto getOrGenerateAiAnalysis(
            @PathVariable Long jobProfileId,
            @RequestParam String studentId,
            @RequestHeader("Authorization") String authorization
    ) {

        return jobAiAnalysisService.getOrGenerateAiAnalysis(
                jobProfileId,
                studentId,
                authorization
        );
    }
}