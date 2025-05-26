package com.ee_stats.initial.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ee_stats.initial.models.Company;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
    Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Company findByRegistryCode(String registryCode);

    Page<Company> findAll(Pageable pageable);
}
