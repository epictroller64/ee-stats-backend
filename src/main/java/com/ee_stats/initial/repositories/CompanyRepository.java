package com.ee_stats.initial.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ee_stats.initial.models.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Iterable<Company> findByRegistryCode(String registryCode);
}
