package com.ee_stats.initial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ee_stats.initial.models.Directorship;
import com.ee_stats.initial.repositories.DirectorshipRepository;

@Service
public class DirectorshipService {

    @Autowired
    private DirectorshipRepository directorshipRepository;

    public List<Directorship> findByRegistryCode(String registryCode) {
        return directorshipRepository.findByRegistryCode(registryCode);
    }

    public Page<Directorship> findAll(Integer page, Integer size) {
        return directorshipRepository.findAll(PageRequest.of(page, size));
    }
}
