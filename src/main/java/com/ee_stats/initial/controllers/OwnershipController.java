package com.ee_stats.initial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee_stats.initial.models.Ownership;
import com.ee_stats.initial.services.OwnershipService;

@RestController
@RequestMapping("/ownerships")
public class OwnershipController {

    @Autowired
    private OwnershipService ownershipService;

    @GetMapping("/find/{registryCode}")
    public List<Ownership> findByRegistryCode(@PathVariable String registryCode) {
        return ownershipService.findByRegistryCode(registryCode);
    }

    @GetMapping("/all/{page}/{size}")
    public Page<Ownership> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return ownershipService.findAll(page, size);
    }
}