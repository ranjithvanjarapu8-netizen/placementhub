package com.placementhub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "job_ai_analysis",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "job_profile_id")
        }
)
public class JobAiAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One AI analysis belongs to exactly one job
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "job_profile_id",
            nullable = false,
            unique = true
    )
    private JobProfile jobProfile;

    // AI-generated summary of the job
    @Column(columnDefinition = "TEXT")
    private String summary;

    // Skills/topics extracted from the JD
    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

    // Expected technical/OA topics
    @Column(columnDefinition = "TEXT")
    private String oaTopics;

    // Expected difficulty
    private String oaDifficulty;

    // Preparation recommendations
    @Column(columnDefinition = "TEXT")
    private String preparationTopics;

    // Complete AI-generated analysis if required
    @Column(columnDefinition = "TEXT")
    private String analysis;

    private LocalDateTime analyzedAt;

    public JobAiAnalysis() {
    }

    public Long getId() {
        return id;
    }

    public JobProfile getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(JobProfile jobProfile) {
        this.jobProfile = jobProfile;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getOaTopics() {
        return oaTopics;
    }

    public void setOaTopics(String oaTopics) {
        this.oaTopics = oaTopics;
    }

    public String getOaDifficulty() {
        return oaDifficulty;
    }

    public void setOaDifficulty(String oaDifficulty) {
        this.oaDifficulty = oaDifficulty;
    }

    public String getPreparationTopics() {
        return preparationTopics;
    }

    public void setPreparationTopics(String preparationTopics) {
        this.preparationTopics = preparationTopics;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}