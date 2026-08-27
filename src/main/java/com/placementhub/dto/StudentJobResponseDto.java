package com.placementhub.dto;

import java.time.LocalDateTime;

public class StudentJobResponseDto {

    private Long studentJobId;

    private Long jobId;

    private String title;

    private String companyName;

    private String companyLogoUuid;

    private String location;

    private String positionType;

    private Long ctcMin;

    private Long ctcMax;

    private String ctcInterval;

    private String ctcCurrency;

    private Long effectiveCTC;

    private String jobStatus;

    private String applicationStatus;

    private LocalDateTime appliedAt;

    private Integer currentStage;

    private Integer rankByStudent;
    private Long applicationDeadline;
    private Long supersetCreatedAt;
    public StudentJobResponseDto() {
    }

    public Long getStudentJobId() {
        return studentJobId;
    }

    public void setStudentJobId(Long studentJobId) {
        this.studentJobId = studentJobId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }
    public Long getSupersetCreatedAt() {
        return supersetCreatedAt;
    }

    public void setSupersetCreatedAt(Long supersetCreatedAt) {
        this.supersetCreatedAt = supersetCreatedAt;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Long applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
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

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Integer getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Integer currentStage) {
        this.currentStage = currentStage;
    }

    public Integer getRankByStudent() {
        return rankByStudent;
    }

    public void setRankByStudent(Integer rankByStudent) {
        this.rankByStudent = rankByStudent;
    }
}