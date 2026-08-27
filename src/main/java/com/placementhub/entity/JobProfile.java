package com.placementhub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "job_profiles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "supersetJobProfileId")
        }
)
public class JobProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String supersetJobProfileId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(
            mappedBy = "jobProfile",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobDocument> documents = new ArrayList<>();
    
    @Column(nullable = false)
    private String title;

    private String location;

    private String positionType;

    // Unix timestamp in milliseconds from Superset
    private Long applicationDeadline;

    private String status;

    private Integer currentStage;

    private String placementName;

    private String placementUuid;

    private String placementCategoryName;

    private String placementCategoryUuid;

    private Integer placementCategoryLevel;

    private String companyLogoUuid;

    private Boolean ppo;

    private Long supersetCreatedAt;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private LocalDateTime lastSyncedAt;
    private Long ctcMin;

    private Long ctcMax;

    private String ctcInterval;

    private String ctcCurrency;

    @Column(columnDefinition = "TEXT")
    private String ctcAdditionalInfo;

    @Column(columnDefinition = "TEXT")
    private String ctcEquityDescription;

    private Long effectiveCTC;
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

	public List<JobDocument> getDocuments() {
	    return documents;
	}

	public void setDocuments(List<JobDocument> documents) {
	    this.documents = documents;
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

	public JobProfile() {
    }

    public Long getId() {
        return id;
    }

    public String getSupersetJobProfileId() {
        return supersetJobProfileId;
    }

    public Company getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getPositionType() {
        return positionType;
    }

    public Long getApplicationDeadline() {
        return applicationDeadline;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCurrentStage() {
        return currentStage;
    }

    public String getPlacementName() {
        return placementName;
    }

    public String getPlacementUuid() {
        return placementUuid;
    }

    public String getPlacementCategoryName() {
        return placementCategoryName;
    }

    public String getPlacementCategoryUuid() {
        return placementCategoryUuid;
    }

    public Integer getPlacementCategoryLevel() {
        return placementCategoryLevel;
    }

    public String getCompanyLogoUuid() {
        return companyLogoUuid;
    }

    public Boolean getPpo() {
        return ppo;
    }

    public Long getSupersetCreatedAt() {
        return supersetCreatedAt;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupersetJobProfileId(String supersetJobProfileId) {
        this.supersetJobProfileId = supersetJobProfileId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public void setApplicationDeadline(Long applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentStage(Integer currentStage) {
        this.currentStage = currentStage;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public void setPlacementUuid(String placementUuid) {
        this.placementUuid = placementUuid;
    }

    public void setPlacementCategoryName(String placementCategoryName) {
        this.placementCategoryName = placementCategoryName;
    }

    public void setPlacementCategoryUuid(String placementCategoryUuid) {
        this.placementCategoryUuid = placementCategoryUuid;
    }

    public void setPlacementCategoryLevel(Integer placementCategoryLevel) {
        this.placementCategoryLevel = placementCategoryLevel;
    }

    public void setCompanyLogoUuid(String companyLogoUuid) {
        this.companyLogoUuid = companyLogoUuid;
    }

    public void setPpo(Boolean ppo) {
        this.ppo = ppo;
    }

    public void setSupersetCreatedAt(Long supersetCreatedAt) {
        this.supersetCreatedAt = supersetCreatedAt;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }
}