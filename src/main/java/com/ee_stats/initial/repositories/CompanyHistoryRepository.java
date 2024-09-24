package com.ee_stats.initial.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ee_stats.initial.models.CompanyHistory;

public interface CompanyHistoryRepository extends CrudRepository<CompanyHistory, Integer> {

    CompanyHistory findByRegistryCode(String registryCode);
}
