package com.placementhub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "student_jobs",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"student_id", "job_profile_id"}
                )
        }
)
public class StudentJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_profile_id", nullable = false)
    private JobProfile jobProfile;

    private String applicationStatus;

    private LocalDateTime appliedAt;

    private Integer currentStage;

    private Integer rankByStudent;

    private Boolean hasEligibilityDataChangedAfterApplying;

    private Boolean isEligibleAfterDataChange;

    private Boolean ineligibleBasedOnCustomQuestions;

    private Boolean rejectedDueToEligibilityDataChange;

    private Boolean eligibilityHistoryAvailable;

    private LocalDateTime lastSyncedAt;

    public StudentJob() {
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public JobProfile getJobProfile() {
        return jobProfile;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public Integer getCurrentStage() {
        return currentStage;
    }

    public Integer getRankByStudent() {
        return rankByStudent;
    }

    public Boolean getHasEligibilityDataChangedAfterApplying() {
        return hasEligibilityDataChangedAfterApplying;
    }

    public Boolean getIsEligibleAfterDataChange() {
        return isEligibleAfterDataChange;
    }

    public Boolean getIneligibleBasedOnCustomQuestions() {
        return ineligibleBasedOnCustomQuestions;
    }

    public Boolean getRejectedDueToEligibilityDataChange() {
        return rejectedDueToEligibilityDataChange;
    }

    public Boolean getEligibilityHistoryAvailable() {
        return eligibilityHistoryAvailable;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setJobProfile(JobProfile jobProfile) {
        this.jobProfile = jobProfile;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public void setCurrentStage(Integer currentStage) {
        this.currentStage = currentStage;
    }

    public void setRankByStudent(Integer rankByStudent) {
        this.rankByStudent = rankByStudent;
    }

    public void setHasEligibilityDataChangedAfterApplying(
            Boolean value) {
        this.hasEligibilityDataChangedAfterApplying = value;
    }

    public void setIsEligibleAfterDataChange(Boolean value) {
        this.isEligibleAfterDataChange = value;
    }

    public void setIneligibleBasedOnCustomQuestions(Boolean value) {
        this.ineligibleBasedOnCustomQuestions = value;
    }

    public void setRejectedDueToEligibilityDataChange(Boolean value) {
        this.rejectedDueToEligibilityDataChange = value;
    }

    public void setEligibilityHistoryAvailable(Boolean value) {
        this.eligibilityHistoryAvailable = value;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }
}