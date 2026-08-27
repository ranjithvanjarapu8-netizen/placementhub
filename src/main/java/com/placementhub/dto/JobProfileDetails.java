package com.placementhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobProfileDetails {

    private Long ctcMin;
    private Long ctcMax;
    private String ctcInterval;
    private String ctcCurrency;
    private String ctcAdditionalInfo;
    private String ctcEquityDescription;
    private Long effectiveCTC;

    private String jobDescription;
    private String location;
    private String title;
    private String positionType;
    private String status;

    public JobProfileDetails() {
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}