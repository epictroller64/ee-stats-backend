package com.ee_stats.initial.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.models.CompanyHistory;
import com.ee_stats.initial.models.HistoryYear;
import com.ee_stats.initial.models.Quarter;
import com.ee_stats.initial.repositories.CompanyHistoryRepository;
import com.ee_stats.initial.repositories.CompanyRepository;

@Service
public class HistoryService {
    private static final Logger logger = LoggerFactory.getLogger(HistoryService.class);
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyHistoryRepository companyHistoryRepository;

    // If already computed, return from the database
    public CompanyHistory getHistoryByRegistryCode(String registryCode) {
        CompanyHistory existingCompanyHistory = companyHistoryRepository.findByRegistryCode(registryCode);
        if (existingCompanyHistory != null) {
            logger.info("Found existing company history for registry code: " + registryCode);
            return existingCompanyHistory;
        }

        logger.info("Computing company history for registry code: " + registryCode);
        Iterable<Company> companies = companyRepository.findByRegistryCode(registryCode);

        CompanyHistory companyHistory = new CompanyHistory();
        companyHistory.setRegistryCode(registryCode);

        HashMap<Integer, List<Quarter>> trackedQuarters = new HashMap<>();

        for (Company company : companies) {
            Quarter quarter = new Quarter(company.getQuarter(), company.getRevenue(), company.getStateTaxes(),
                    company.getLaborTaxes(), company.getEmployees(), company.getYear(), company.getRegistryCode());
            trackedQuarters.computeIfAbsent(company.getYear(), m -> new ArrayList<Quarter>()).add(quarter);
        }

        // Start of Selection
        for (Map.Entry<Integer, List<Quarter>> entry : trackedQuarters.entrySet()) {
            Integer year = entry.getKey();
            List<Quarter> quarters = entry.getValue();
            HistoryYear historyYear = new HistoryYear(registryCode, year);
            for (Quarter quarter : quarters) {
                historyYear.addQuarter(quarter);
            }
            companyHistory.addHistoryYear(historyYear);
        }

        return companyHistoryRepository.save(companyHistory);
    }

}
