package com.placementhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupersetJobProfileDto {

    private String companyCode;
    private String companyName;
    private String companyLogoUuid;

    private String jobProfileIdentifier;
    private String jobProfileTitle;
    private String jobProfileLocation;
    private String positionType;

    private Integer jobProfileCurrentStage;
    private Long jobProfileApplicationDeadline;
    private String jobProfileStatus;

    private String jobApplicationStatus;
    private Integer jobApplicationCurrentStage;
    private Long jobApplicationLastAppliedAt;

    private String placementName;
    private String placementUUID;

    private String placementCategoryName;
    private String placementCategoryUuid;
    private Integer placementCategoryLevel;

    private Integer rankByStudent;

    private Long createdAt;

    private Boolean ppo;

    private Boolean hasEligibilityDataChangedAfterApplying;
    private Boolean isEligibleAfterDataChange;
    private Boolean ineligibleBasedOnCustomQuestions;
    private Boolean rejectedDueToEligibilityDataChange;
    private Boolean eligibilityHistoryAvailable;
    private Long ctcMin;
    private Long ctcMax;
    private String ctcInterval;
    private String ctcCurrency;
    private String ctcAdditionalInfo;
    private String ctcEquityDescription;
    private Long effectiveCTC;
    private Boolean outline;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }
    public Long getCtcMin() {
        return ctcMin;
    }

    public void setCtcMin(Long ctcMin) {
        this.ctcMin = ctcMin;
    }

    public Long getCtcMax() {
        return ctcMax;
    }

    public void setCtcMax(Long ctcMax) {
        this.ctcMax = ctcMax;
    }

    public String getCtcInterval() {
        return ctcInterval;
    }

    public void setCtcInterval(String ctcInterval) {
        this.ctcInterval = ctcInterval;
    }

    public String getCtcCurrency() {
        return ctcCurrency;
    }

    public void setCtcCurrency(String ctcCurrency) {
        this.ctcCurrency = ctcCurrency;
    }

    public String getCtcAdditionalInfo() {
        return ctcAdditionalInfo;
    }

    public void setCtcAdditionalInfo(String ctcAdditionalInfo) {
        this.ctcAdditionalInfo = ctcAdditionalInfo;
    }

    public String getCtcEquityDescription() {
        return ctcEquityDescription;
    }

    public void setCtcEquityDescription(String ctcEquityDescription) {
        this.ctcEquityDescription = ctcEquityDescription;
    }

    public Long getEffectiveCTC() {
        return effectiveCTC;
    }

    public void setEffectiveCTC(Long effectiveCTC) {
        this.effectiveCTC = effectiveCTC;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogoUuid() {
        return companyLogoUuid;
    }

    public void setCompanyLogoUuid(String companyLogoUuid) {
        this.companyLogoUuid = companyLogoUuid;
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

    public Integer getJobProfileCurrentStage() {
        return jobProfileCurrentStage;
    }

    public void setJobProfileCurrentStage(Integer jobProfileCurrentStage) {
        this.jobProfileCurrentStage = jobProfileCurrentStage;
    }

    public Long getJobProfileApplicationDeadline() {
        return jobProfileApplicationDeadline;
    }

    public void setJobProfileApplicationDeadline(
            Long jobProfileApplicationDeadline) {
        this.jobProfileApplicationDeadline =
                jobProfileApplicationDeadline;
    }

    public String getJobProfileStatus() {
        return jobProfileStatus;
    }

    public void setJobProfileStatus(String jobProfileStatus) {
        this.jobProfileStatus = jobProfileStatus;
    }

    public String getJobApplicationStatus() {
        return jobApplicationStatus;
    }

    public void setJobApplicationStatus(String jobApplicationStatus) {
        this.jobApplicationStatus = jobApplicationStatus;
    }

    public Integer getJobApplicationCurrentStage() {
        return jobApplicationCurrentStage;
    }

    public void setJobApplicationCurrentStage(
            Integer jobApplicationCurrentStage) {
        this.jobApplicationCurrentStage =
                jobApplicationCurrentStage;
    }

    public Long getJobApplicationLastAppliedAt() {
        return jobApplicationLastAppliedAt;
    }

    public void setJobApplicationLastAppliedAt(
            Long jobApplicationLastAppliedAt) {
        this.jobApplicationLastAppliedAt =
                jobApplicationLastAppliedAt;
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

    public String getPlacementCategoryName() {
        return placementCategoryName;
    }

    public void setPlacementCategoryName(String placementCategoryName) {
        this.placementCategoryName = placementCategoryName;
    }

    public String getPlacementCategoryUuid() {
        return placementCategoryUuid;
    }

    public void setPlacementCategoryUuid(String placementCategoryUuid) {
        this.placementCategoryUuid = placementCategoryUuid;
    }

    public Integer getPlacementCategoryLevel() {
        return placementCategoryLevel;
    }

    public void setPlacementCategoryLevel(Integer placementCategoryLevel) {
        this.placementCategoryLevel = placementCategoryLevel;
    }

    public Integer getRankByStudent() {
        return rankByStudent;
    }

    public void setRankByStudent(Integer rankByStudent) {
        this.rankByStudent = rankByStudent;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPpo() {
        return ppo;
    }

    public void setPpo(Boolean ppo) {
        this.ppo = ppo;
    }

    public Boolean getHasEligibilityDataChangedAfterApplying() {
        return hasEligibilityDataChangedAfterApplying;
    }

    public void setHasEligibilityDataChangedAfterApplying(
            Boolean value) {
        this.hasEligibilityDataChangedAfterApplying = value;
    }

    public Boolean getIsEligibleAfterDataChange() {
        return isEligibleAfterDataChange;
    }

    public void setIsEligibleAfterDataChange(Boolean value) {
        this.isEligibleAfterDataChange = value;
    }

    public Boolean getIneligibleBasedOnCustomQuestions() {
        return ineligibleBasedOnCustomQuestions;
    }

    public void setIneligibleBasedOnCustomQuestions(Boolean value) {
        this.ineligibleBasedOnCustomQuestions = value;
    }

    public Boolean getRejectedDueToEligibilityDataChange() {
        return rejectedDueToEligibilityDataChange;
    }

    public void setRejectedDueToEligibilityDataChange(Boolean value) {
        this.rejectedDueToEligibilityDataChange = value;
    }

    public Boolean getEligibilityHistoryAvailable() {
        return eligibilityHistoryAvailable;
    }

    public void setEligibilityHistoryAvailable(Boolean value) {
        this.eligibilityHistoryAvailable = value;
    }

    public Boolean getOutline() {
        return outline;
    }

    public void setOutline(Boolean outline) {
        this.outline = outline;
    }
}