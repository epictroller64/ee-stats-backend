package com.ee_stats.initial.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_history")
public class CompanyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "company_history_id_seq", initialValue = 1)
    private Integer id;

    @Column(name = "registry_code")
    private String registryCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "companyHistory")
    private List<HistoryYear> historyYears;

    public CompanyHistory() {
    }

    public CompanyHistory(String registryCode, List<HistoryYear> historyYears) {
        this.registryCode = registryCode;
        this.historyYears = historyYears;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public List<HistoryYear> getHistoryYears() {
        return historyYears;
    }

    public void setRegistryCode(String registryCode) {
        this.registryCode = registryCode;
    }

    public void addHistoryYear(HistoryYear historyYear) {
        if (historyYear != null) {
            if (this.historyYears == null) {
                this.historyYears = new ArrayList<>();
            }
            if (!this.historyYears.contains(historyYear)) {
                this.historyYears.add(historyYear);
                historyYear.setCompanyHistory(this);
            }
        }
    }
}
