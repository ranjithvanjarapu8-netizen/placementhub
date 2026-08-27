package com.placementhub.service;

import com.placementhub.dto.SupersetJobDetailsDto;
import com.placementhub.dto.SupersetJobProfileDetailsDto;
import com.placementhub.dto.SupersetJobProfileDto;
import com.placementhub.dto.SupersetLoginRequest;
import com.placementhub.dto.SupersetLoginResponse;
import com.placementhub.dto.SupersetStudentDto;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class SupersetClient {

    private final RestClient restClient;

    public SupersetClient() {

        this.restClient = RestClient.builder()
                .baseUrl("https://app.joinsuperset.com")
                .build();
    }

    // ============================================================
    // GET ALL JOB PROFILES
    // ============================================================

    public List<SupersetJobProfileDto> getJobProfiles(
            String studentId,
            String authorization
    ) {

        return restClient.get()
                .uri(
                        "/tnpsuite-core/students/{studentId}/job_profiles?_loader_=false",
                        studentId
                )
                .header(
                        HttpHeaders.AUTHORIZATION,
                        "Custom " + authorization
                )
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .retrieve()
                .body(
                        new ParameterizedTypeReference<
                                List<SupersetJobProfileDto>>() {
                        }
                );
    }

    // ============================================================
    // GET DETAILS OF ONE PARTICULAR JOB PROFILE
    // ============================================================

    public SupersetJobProfileDetailsDto getJobProfileDetails(
            String studentId,
            String jobProfileId,
            String authorization
    ) {

        String url =
                "/tnpsuite-core/students/"
                + studentId
                + "/job_profiles/"
                + jobProfileId
                + "?_loader_=false";

        return restClient.get()
                .uri(url)
                .header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                )
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .retrieve()
                .body(SupersetJobProfileDetailsDto.class);
    }
    public String getRawJobProfileDetails(
            String studentId,
            String jobProfileId,
            String authorization
    ) {

        String url =
                "/tnpsuite-core/students/"
                + studentId
                + "/job_profiles/"
                + jobProfileId
                + "?_loader_=false";

        return restClient.get()
                .uri(url)
                .header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                )
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .retrieve()
                .body(String.class);
    }
    public String getDocumentUrl(
            String studentId,
            String jobProfileId,
            String documentId,
            String authorization
    ) {

        String url =
                "/tnpsuite-core/students/"
                + studentId
                + "/job_profiles/"
                + jobProfileId
                + "/documents/"
                + documentId
                + "/url";

        return restClient.get()
                .uri(url)
                .header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                )
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .retrieve()
                .body(DocumentUrlResponse.class)
                .getUrl();
    }
    public SupersetStudentDto getStudent(
            String studentId,
            String authorization
    ) {

        return restClient.get()
                .uri(
                        "/tnpsuite-core/students/{studentId}?_loader_=false",
                        studentId
                )
                .header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                )
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .retrieve()
                .body(SupersetStudentDto.class);
    }
    public static class DocumentUrlResponse {

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public byte[] downloadDocument(String documentUrl) {

        System.out.println("DOCUMENT URL = " + documentUrl);

        RestClient documentClient = RestClient.builder()
                .build();

        return documentClient.get()
                .uri(java.net.URI.create(documentUrl))
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_PDF_VALUE
                )
                .retrieve()
                .body(byte[].class);
    }
    public SupersetLoginResponse login(
            String username,
            String password
    ) {

        SupersetLoginRequest request =
                new SupersetLoginRequest(
                        username,
                        password
                );

        return restClient.post()
                .uri("/tnpsuite-core/login")
                .contentType(MediaType.APPLICATION_JSON)
                .header(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .body(request)
                .retrieve()
                .body(SupersetLoginResponse.class);
    }
    
}