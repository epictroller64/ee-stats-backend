package com.ee_stats.initial.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyDTO {
    private UUID id;
    private String registryCode;
    private String name;
    private String type;
    private String businessArea;
    private String county;
    private Boolean isVat;

    // Constructors, getters, and setters
    public CompanyDTO(UUID id, String registryCode, String name, String type, String businessArea, String county,
            Boolean isVat) {
        this.id = id;
        this.registryCode = registryCode;
        this.name = name;
        this.type = type;
        this.businessArea = businessArea;
        this.county = county;
        this.isVat = isVat;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public void setRegistryCode(String registryCode) {
        this.registryCode = registryCode;
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

    public Boolean getIsVat() {
        return isVat;
    }

    public void setIsVat(Boolean isVat) {
        this.isVat = isVat;
    }
}
