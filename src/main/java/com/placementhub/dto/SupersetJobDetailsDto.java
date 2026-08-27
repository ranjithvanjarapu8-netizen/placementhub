package com.placementhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupersetJobDetailsDto {

    private String jobProfileIdentifier;
    private String jobProfileTitle;
    private String companyName;
    private String companyCode;
    private String jobProfileLocation;
    private String positionType;
    private String jobProfileStatus;
    private Long jobProfileApplicationDeadline;

    private String placementName;
    private String placementUUID;

    // IMPORTANT:
    // CTC, job description, etc. are inside this object
    private JobProfileDetails jobProfile;

    public SupersetJobDetailsDto() {
    }

    public String getJobProfileIdentifier() {
        return jobProfileIdentifier;
    }

    public void setJobProfileIdentifier(String jobProfileIdentifier) {
        this.jobProfileIdentifier = jobProfileIdentifier;
    }

    public String getJobProfileTitle() {
        return jobProfileTitle;
    }

    public void setJobProfileTitle(String jobProfileTitle) {
        this.jobProfileTitle = jobProfileTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getJobProfileLocation() {
        return jobProfileLocation;
    }

    public void setJobProfileLocation(String jobProfileLocation) {
        this.jobProfileLocation = jobProfileLocation;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getJobProfileStatus() {
        return jobProfileStatus;
    }

    public void setJobProfileStatus(String jobProfileStatus) {
        this.jobProfileStatus = jobProfileStatus;
    }

    public Long getJobProfileApplicationDeadline() {
        return jobProfileApplicationDeadline;
    }

    public void setJobProfileApplicationDeadline(Long jobProfileApplicationDeadline) {
        this.jobProfileApplicationDeadline = jobProfileApplicationDeadline;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public String getPlacementUUID() {
        return placementUUID;
    }

    public void setPlacementUUID(String placementUUID) {
        this.placementUUID = placementUUID;
    }

    public JobProfileDetails getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfileDetails jobProfile) {
        this.jobProfile = jobProfile;
    }
}