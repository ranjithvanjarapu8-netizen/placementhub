package com.placementhub.dto;

import java.time.LocalDateTime;

public class JobAiAnalysisDto {

    private Long id;

    private Long jobProfileId;

    private String summary;

    private String requiredSkills;

    private String oaTopics;

    private String oaDifficulty;

    private String preparationTopics;

    private String analysis;

    private LocalDateTime analyzedAt;

    public JobAiAnalysisDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobProfileId() {
        return jobProfileId;
    }

    public void setJobProfileId(Long jobProfileId) {
        this.jobProfileId = jobProfileId;
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