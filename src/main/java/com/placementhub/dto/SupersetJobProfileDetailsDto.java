package com.placementhub.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupersetJobProfileDetailsDto {

    private String companyCode;
    private String companyName;

    private JobProfileDetails jobProfile;

    private Long jobProfileApplicationDeadline;
    private String jobProfileIdentifier;
    private String jobProfileLocation;
    private String jobProfileStatus;
    private String jobProfileTitle;

    private String placementName;
    private String placementUUID;
    private String positionType;

    // Top-level documents
    private List<DocumentDto> documents;

    public SupersetJobProfileDetailsDto() {
    }

    // =====================================================
    // GETTERS / SETTERS
    // =====================================================

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public JobProfileDetails getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfileDetails jobProfile) {
        this.jobProfile = jobProfile;
    }

    public Long getJobProfileApplicationDeadline() {
        return jobProfileApplicationDeadline;
    }

    public void setJobProfileApplicationDeadline(
            Long jobProfileApplicationDeadline
    ) {
        this.jobProfileApplicationDeadline =
                jobProfileApplicationDeadline;
    }

    public String getJobProfileIdentifier() {
        return jobProfileIdentifier;
    }

    public void setJobProfileIdentifier(
            String jobProfileIdentifier
    ) {
        this.jobProfileIdentifier =
                jobProfileIdentifier;
    }

    public String getJobProfileLocation() {
        return jobProfileLocation;
    }

    public void setJobProfileLocation(
            String jobProfileLocation
    ) {
        this.jobProfileLocation =
                jobProfileLocation;
    }

    public String getJobProfileStatus() {
        return jobProfileStatus;
    }

    public void setJobProfileStatus(
            String jobProfileStatus
    ) {
        this.jobProfileStatus =
                jobProfileStatus;
    }

    public String getJobProfileTitle() {
        return jobProfileTitle;
    }

    public void setJobProfileTitle(
            String jobProfileTitle
    ) {
        this.jobProfileTitle =
                jobProfileTitle;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(
            String placementName
    ) {
        this.placementName =
                placementName;
    }

    public String getPlacementUUID() {
        return placementUUID;
    }

    public void setPlacementUUID(
            String placementUUID
    ) {
        this.placementUUID =
                placementUUID;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(
            String positionType
    ) {
        this.positionType =
                positionType;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(
            List<DocumentDto> documents
    ) {
        this.documents = documents;
    }

    // =====================================================
    // JOB PROFILE DETAILS
    // =====================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JobProfileDetails {

        private String ctcAdditionalInfo;
        private String ctcCurrency;
        private String ctcEquityDescription;
        private String ctcInterval;

        private Long ctcMax;
        private Long ctcMin;
        private Long effectiveCTC;

        private String jobDescription;

        private List<DocumentDto> documents;

        private String location;
        private String positionType;
        private String status;
        private String title;

        public JobProfileDetails() {
        }

        // =================================================
        // GETTERS / SETTERS
        // =================================================

        public String getCtcAdditionalInfo() {
            return ctcAdditionalInfo;
        }

        public void setCtcAdditionalInfo(
                String ctcAdditionalInfo
        ) {
            this.ctcAdditionalInfo =
                    ctcAdditionalInfo;
        }

        public String getCtcCurrency() {
            return ctcCurrency;
        }

        public void setCtcCurrency(
                String ctcCurrency
        ) {
            this.ctcCurrency =
                    ctcCurrency;
        }

        public String getCtcEquityDescription() {
            return ctcEquityDescription;
        }

        public void setCtcEquityDescription(
                String ctcEquityDescription
        ) {
            this.ctcEquityDescription =
                    ctcEquityDescription;
        }

        public String getCtcInterval() {
            return ctcInterval;
        }

        public void setCtcInterval(
                String ctcInterval
        ) {
            this.ctcInterval =
                    ctcInterval;
        }

        public Long getCtcMax() {
            return ctcMax;
        }

        public void setCtcMax(Long ctcMax) {
            this.ctcMax =
                    ctcMax;
        }

        public Long getCtcMin() {
            return ctcMin;
        }

        public void setCtcMin(Long ctcMin) {
            this.ctcMin =
                    ctcMin;
        }

        public Long getEffectiveCTC() {
            return effectiveCTC;
        }

        public void setEffectiveCTC(
                Long effectiveCTC
        ) {
            this.effectiveCTC =
                    effectiveCTC;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(
                String jobDescription
        ) {
            this.jobDescription =
                    jobDescription;
        }

        public List<DocumentDto> getDocuments() {
            return documents;
        }

        public void setDocuments(
                List<DocumentDto> documents
        ) {
            this.documents =
                    documents;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(
                String location
        ) {
            this.location =
                    location;
        }

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(
                String positionType
        ) {
            this.positionType =
                    positionType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(
                String status
        ) {
            this.status =
                    status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(
                String title
        ) {
            this.title =
                    title;
        }
    }

    // =====================================================
    // DOCUMENT DTO
    // =====================================================

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DocumentDto {

        private String contentType;
        private String identifier;
        private String name;
        private String type;
        private String url;
        private String uuid;

        public DocumentDto() {
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(
                String contentType
        ) {
            this.contentType =
                    contentType;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(
                String identifier
        ) {
            this.identifier =
                    identifier;
        }

        public String getName() {
            return name;
        }

        public void setName(
                String name
        ) {
            this.name =
                    name;
        }

        public String getType() {
            return type;
        }

        public void setType(
                String type
        ) {
            this.type =
                    type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(
                String url
        ) {
            this.url =
                    url;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(
                String uuid
        ) {
            this.uuid =
                    uuid;
        }
    }
}