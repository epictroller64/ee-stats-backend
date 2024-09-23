package com.ee_stats.initial.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quarter;

    private Integer year;

    @Column(name = "registry_code")
    private String registryCode;

    private String name;

    private String type;

    @Column(name = "registered_vat")
    private Boolean registeredVat;

    @Column(name = "business_area")
    private String businessArea;

    private String county;

    @Column(name = "state_taxes", precision = 10)
    private Float stateTaxes;

    @Column(name = "labor_taxes", precision = 10)
    private Float laborTaxes;

    @Column(precision = 10)
    private Float revenue;

    private Integer employees;

    private Company() {
    }

    public Company(String registryCode, String name, String type, Boolean registeredVat, String businessArea,
            String county, Float stateTaxes, Float laborTaxes, Float revenue, Integer employees, Integer quarter,
            Integer year) {
        this.quarter = quarter;
        this.year = year;
        this.registryCode = registryCode;
        this.name = name;
        this.type = type;
        this.registeredVat = registeredVat;
        this.businessArea = businessArea;
        this.county = county;
        this.stateTaxes = stateTaxes;
        this.laborTaxes = laborTaxes;
        this.revenue = revenue;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public Integer getYear() {
        return year;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getRegisteredVat() {
        return registeredVat;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public String getCounty() {
        return county;
    }

    public Float getStateTaxes() {
        return stateTaxes;
    }

    public Float getLaborTaxes() {
        return laborTaxes;
    }

    public Float getRevenue() {
        return revenue;
    }

    public Integer getEmployees() {
        return employees;
    }
}
