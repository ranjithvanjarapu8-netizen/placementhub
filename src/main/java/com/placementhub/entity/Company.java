package com.placementhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String supersetCompanyId;

    private String logoUuid;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSupersetCompanyId() {
        return supersetCompanyId;
    }

    public String getLogoUuid() {
        return logoUuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSupersetCompanyId(String supersetCompanyId) {
        this.supersetCompanyId = supersetCompanyId;
    }

    public void setLogoUuid(String logoUuid) {
        this.logoUuid = logoUuid;
    }
}