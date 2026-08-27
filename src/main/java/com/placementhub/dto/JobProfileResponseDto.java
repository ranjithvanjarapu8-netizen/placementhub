package com.placementhub.dto;

public class JobProfileResponseDto {

    private Long id;

    private String supersetJobProfileId;

    private String title;

    private String companyName;

    private String companyLogoUuid;

    private String location;

    private String positionType;

    private Long applicationDeadline;

    private String status;

    private String placementName;

    private String placementCategoryName;

    private Long ctcMin;

    private Long ctcMax;

    private String ctcInterval;

    private String ctcCurrency;

    private Long effectiveCTC;

    private Boolean ppo;
    private Long supersetCreatedAt;
    public JobProfileResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Long getSupersetCreatedAt() {
        return supersetCreatedAt;
    }
    public void setSupersetCreatedAt(Long supersetCreatedAt) {
        this.supersetCreatedAt = supersetCreatedAt;
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

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public String getPlacementCategoryName() {
        return placementCategoryName;
    }

    public void setPlacementCategoryName(String placementCategoryName) {
        this.placementCategoryName = placementCategoryName;
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

    public Long getEffectiveCTC() {
        return effectiveCTC;
    }

    public void setEffectiveCTC(Long effectiveCTC) {
        this.effectiveCTC = effectiveCTC;
    }

    public Boolean getPpo() {
        return ppo;
    }

    public void setPpo(Boolean ppo) {
        this.ppo = ppo;
    }
}