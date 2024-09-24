package com.ee_stats.initial.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "quarters")
public class Quarter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "quarters_id_seq", initialValue = 1)
    private Integer id;

    @Column(name = "quarter_number")
    private Integer quarter;
    private Integer year;
    @Column(name = "registry_code")
    private String registryCode;
    private BigDecimal revenue;
    @Column(name = "state_taxes")
    private BigDecimal stateTaxes;
    @Column(name = "labor_taxes")
    private BigDecimal laborTaxes;
    private Integer employees;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_year_id", referencedColumnName = "id")
    private HistoryYear historyYear;

    public Quarter() {
    }

    public Quarter(Integer quarter, BigDecimal revenue, BigDecimal stateTaxes, BigDecimal laborTaxes,
            Integer employees, Integer year, String registryCode) {
        this.quarter = quarter;
        this.registryCode = registryCode;
        this.revenue = revenue;
        this.stateTaxes = stateTaxes;
        this.laborTaxes = laborTaxes;
        this.employees = employees;
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getStateTaxes() {
        return stateTaxes;
    }

    public BigDecimal getLaborTaxes() {
        return laborTaxes;
    }

    public Integer getEmployees() {
        return employees;
    }

    public Integer getYear() {
        return year;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public void setHistoryYear(HistoryYear historyYear) {
        this.historyYear = historyYear;
        if (historyYear != null && !historyYear.getQuarters().contains(this)) {
            historyYear.getQuarters().add(this);
        }
    }

    public HistoryYear getHistoryYear() {
        return historyYear;
    }

}
