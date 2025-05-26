package com.ee_stats.initial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee_stats.initial.models.Directorship;
import com.ee_stats.initial.services.DirectorshipService;

@RestController
@RequestMapping("/directorships")
public class DirectorshipController {

    @Autowired
    private DirectorshipService directorshipService;

    @GetMapping("/find/{registryCode}")
    public List<Directorship> findByRegistryCode(@PathVariable String registryCode) {
        return directorshipService.findByRegistryCode(registryCode);
    }

    @GetMapping("/all/{page}/{size}")
    public Page<Directorship> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return directorshipService.findAll(page, size);
    }
}