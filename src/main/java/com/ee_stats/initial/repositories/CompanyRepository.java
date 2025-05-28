package com.ee_stats.initial.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ee_stats.initial.models.Company;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
    Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Company findByRegistryCode(String registryCode);

    Page<Company> findAll(Pageable pageable);

    @Query("SELECT c FROM Company c JOIN Ownership o ON c.registryCode = o.registryCode WHERE o.ownerId.id = :ownerId")
    List<Company> findByOwnerId(@Param("ownerId") String ownerId);

    @Query("SELECT c FROM Company c JOIN Directorship d ON c.registryCode = d.registryCode WHERE d.directorId.id = :directorId")
    List<Company> findByDirectorId(@Param("directorId") String directorId);
}
