package com.placementhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupersetStudentDto {

    private String uuid;

    private String fullName;

    private String email;

    private String identificationNumber;

    private String mobile;

    private String collegeName;

    private String defaultResumeId;

    private Invitation invitation;

    public SupersetStudentDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDefaultResumeId() {
        return defaultResumeId;
    }

    public void setDefaultResumeId(String defaultResumeId) {
        this.defaultResumeId = defaultResumeId;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Invitation {

        private String currentCourseScoreValue;

        private String currentCourse;

        private Batch batch;

        public String getCurrentCourseScoreValue() {
            return currentCourseScoreValue;
        }

        public void setCurrentCourseScoreValue(String currentCourseScoreValue) {
            this.currentCourseScoreValue = currentCourseScoreValue;
        }

        public String getCurrentCourse() {
            return currentCourse;
        }

        public void setCurrentCourse(String currentCourse) {
            this.currentCourse = currentCourse;
        }

        public Batch getBatch() {
            return batch;
        }

        public void setBatch(Batch batch) {
            this.batch = batch;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Batch {

        private String name;

        private Integer year;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }
    }
}