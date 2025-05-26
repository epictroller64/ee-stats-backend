package com.ee_stats.initial.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ee_stats.initial.models.Ownership;

public interface OwnershipRepository extends CrudRepository<Ownership, String> {
    List<Ownership> findByRegistryCode(String registryCode);

    Page<Ownership> findAll(Pageable pageable);
}