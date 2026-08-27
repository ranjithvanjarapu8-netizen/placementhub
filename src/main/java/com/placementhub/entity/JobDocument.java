package com.placementhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_documents")
public class JobDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_profile_id", nullable = false)
    private JobProfile jobProfile;

    @Column(nullable = false)
    private String supersetDocumentId;

    private String name;

    private String type;

    private String contentType;

    public JobDocument() {
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

    public String getSupersetDocumentId() {
        return supersetDocumentId;
    }

    public void setSupersetDocumentId(String supersetDocumentId) {
        this.supersetDocumentId = supersetDocumentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}