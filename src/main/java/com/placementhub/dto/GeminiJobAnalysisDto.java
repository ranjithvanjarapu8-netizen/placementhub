package com.placementhub.dto;

public class GeminiJobAnalysisDto {

    private String summary;

    private String requiredSkills;

    private String oaTopics;

    private String oaDifficulty;

    private String preparationTopics;

    private String analysis;

    public GeminiJobAnalysisDto() {
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
}