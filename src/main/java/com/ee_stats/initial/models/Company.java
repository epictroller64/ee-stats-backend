package com.ee_stats.initial.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "registry_code")
    private String registryCode;

    @Column(name = "business_area")
    private String businessArea;

    @Column(name = "county")
    private String county;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "is_vat")
    private Boolean isVat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private List<HistoryYear> historyYears;

    public Company() {
    }

    public Company(String registryCode, List<HistoryYear> historyYears) {
        this.registryCode = registryCode;
        this.historyYears = historyYears;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<HistoryYear> getHistoryYears() {
        return historyYears;
    }

    public void setHistoryYears(List<HistoryYear> historyYears) {
        this.historyYears = historyYears;
    }

    public void setRegistryCode(String registryCode) {
        this.registryCode = registryCode;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public Boolean getIsVat() {
        return isVat;
    }

    public void setIsVat(Boolean isVat) {
        this.isVat = isVat;
    }
}
