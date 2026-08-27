package com.placementhub.dto;

import java.util.List;

public class JobProfileDetailsDto {

    private Long id;

    private String supersetJobProfileId;

    private String title;

    private String companyName;

    private String companyLogoUuid;

    private String location;

    private String positionType;

    private Long applicationDeadline;

    private String status;

    private Integer currentStage;

    private String placementName;

    private String placementUuid;

    private String placementCategoryName;

    private String placementCategoryUuid;

    private Integer placementCategoryLevel;

    private Boolean ppo;

    private Long supersetCreatedAt;

    private String jobDescription;
    
    private List<SupersetDocumentDto> documents;

    private Long ctcMin;

    private Long ctcMax;

    private String ctcInterval;

    private String ctcCurrency;

    private String ctcAdditionalInfo;

    private String ctcEquityDescription;

    private Long effectiveCTC;
    private String applicationStatus;
    public JobProfileDetailsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public String getSupersetJobProfileId() {
        return supersetJobProfileId;
    }

    public void setSupersetJobProfileId(String supersetJobProfileId) {
        this.supersetJobProfileId = supersetJobProfileId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<SupersetDocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<SupersetDocumentDto> documents) {
        this.documents = documents;
    }

    public String getCompanyName() {
        return companyName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public Long getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Long applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Integer currentStage) {
        this.currentStage = currentStage;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public String getPlacementUuid() {
        return placementUuid;
    }

    public void setPlacementUuid(String placementUuid) {
        this.placementUuid = placementUuid;
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

    public Boolean getPpo() {
        return ppo;
    }

    public void setPpo(Boolean ppo) {
        this.ppo = ppo;
    }

    public Long getSupersetCreatedAt() {
        return supersetCreatedAt;
    }

    public void setSupersetCreatedAt(Long supersetCreatedAt) {
        this.supersetCreatedAt = supersetCreatedAt;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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
}