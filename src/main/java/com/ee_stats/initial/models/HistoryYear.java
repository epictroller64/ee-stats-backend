package com.ee_stats.initial.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "history_years")
public class HistoryYear {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @Column(name = "registry_code")
    private String registryCode;

    private Integer year;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historyYear")
    private List<Quarter> quarters;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public HistoryYear() {
    }

    public HistoryYear(String registryCode, Integer year) {
        this.registryCode = registryCode;
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public List<Quarter> getQuarters() {
        return this.quarters;
    }

    public Company getCompany() {
        return company;
    }

    public void addQuarter(Quarter quarter) {
        if (quarter != null) {
            if (this.quarters == null) {
                this.quarters = new ArrayList<>();
            }
            if (!this.quarters.contains(quarter)) {
                this.quarters.add(quarter);
                quarter.setHistoryYear(this);
            }
        }

    }

    public void setCompany(Company company) {
        this.company = company;
        if (company != null && !company.getHistoryYears().contains(this)) {
            company.getHistoryYears().add(this);
        }
    }
}
