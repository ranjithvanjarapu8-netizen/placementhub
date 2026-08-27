package com.placementhub.dto;

import java.time.LocalDateTime;

public class JobProfileDto {

    private Long id;

    private String supersetJobProfileId;

    private Long companyId;

    private String companyName;

    private String companyLogoUuid;

    private String title;

    private String location;

    private String positionType;

    private Long applicationDeadline;

    private String status;

    private String currentStage;

    private String placementName;

    private String placementUuid;

    private String placementCategoryName;

    private String placementCategoryUuid;

    private String placementCategoryLevel;

    private Boolean ppo;

    private LocalDateTime supersetCreatedAt;

    private LocalDateTime lastSyncedAt;


    public JobProfileDto() {
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


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
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


    public String getPlacementCategoryLevel() {
        return placementCategoryLevel;
    }

    public void setPlacementCategoryLevel(String placementCategoryLevel) {
        this.placementCategoryLevel = placementCategoryLevel;
    }


    public Boolean getPpo() {
        return ppo;
    }

    public void setPpo(Boolean ppo) {
        this.ppo = ppo;
    }


    public LocalDateTime getSupersetCreatedAt() {
        return supersetCreatedAt;
    }

    public void setSupersetCreatedAt(LocalDateTime supersetCreatedAt) {
        this.supersetCreatedAt = supersetCreatedAt;
    }


    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }
}