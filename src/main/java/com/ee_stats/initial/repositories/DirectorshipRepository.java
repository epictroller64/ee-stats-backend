package com.ee_stats.initial.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ee_stats.initial.models.Directorship;

public interface DirectorshipRepository extends CrudRepository<Directorship, String> {
    List<Directorship> findByRegistryCode(String registryCode);

    Page<Directorship> findAll(Pageable pageable);
}
