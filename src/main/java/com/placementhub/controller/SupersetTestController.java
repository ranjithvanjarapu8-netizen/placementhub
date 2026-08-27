package com.placementhub.controller;

import com.placementhub.dto.SupersetJobDetailsDto;
import com.placementhub.dto.SupersetJobProfileDetailsDto;
import com.placementhub.dto.SupersetJobProfileDto;
import com.placementhub.dto.SupersetStudentDto;
import com.placementhub.service.SupersetClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/superset")
public class SupersetTestController {

    private final SupersetClient supersetClient;

    public SupersetTestController(SupersetClient supersetClient) {
        this.supersetClient = supersetClient;
    }

    @GetMapping("/jobs")
    public List<SupersetJobProfileDto> getJobs(
            @RequestParam String studentId,
            @RequestHeader("Authorization") String authorization
    ) {

        return supersetClient.getJobProfiles(
                studentId,
                authorization
        );
    }
    @GetMapping("/job-details")
    public SupersetJobProfileDetailsDto getJobDetails(

            @RequestParam String studentId,

            @RequestParam String jobProfileId,

            @RequestHeader("Authorization") String authorization

    ) {

        return supersetClient.getJobProfileDetails(

                studentId,

                jobProfileId,

                authorization

        );
    }
    @GetMapping("/raw-job-details")
    public String getRawJobDetails(

            @RequestParam String studentId,

            @RequestParam String jobProfileId,

            @RequestHeader("Authorization") String authorization

    ) {

        return supersetClient.getRawJobProfileDetails(
                studentId,
                jobProfileId,
                authorization
        );
    }
    @GetMapping("/student")
    public SupersetStudentDto getStudent(
            @RequestParam String studentId,
            @RequestHeader("Authorization") String authorization
    ) {

        return supersetClient.getStudent(
                studentId,
                authorization
        );
    }
    @GetMapping("/document-url")
    public String getDocumentUrl(

            @RequestParam String studentId,

            @RequestParam String jobProfileId,

            @RequestParam String documentId,

            @RequestHeader("Authorization") String authorization

    ) {

        return supersetClient.getDocumentUrl(
                studentId,
                jobProfileId,
                documentId,
                authorization
        );
    }
}