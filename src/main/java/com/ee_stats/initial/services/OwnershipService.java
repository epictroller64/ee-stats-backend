package com.ee_stats.initial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ee_stats.initial.models.Ownership;
import com.ee_stats.initial.repositories.OwnershipRepository;

@Service
public class OwnershipService {

    @Autowired
    private OwnershipRepository ownershipRepository;

    public List<Ownership> findByRegistryCode(String registryCode) {
        return ownershipRepository.findByRegistryCode(registryCode);
    }

    public Page<Ownership> findAll(Integer page, Integer size) {
        return ownershipRepository.findAll(PageRequest.of(page, size));
    }
}