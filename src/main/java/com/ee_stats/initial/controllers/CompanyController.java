package com.ee_stats.initial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ee_stats.initial.dto.CompanyDTO;
import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.services.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{registryCode}")
    public CompanyDTO findByRegistryCode(@PathVariable String registryCode) {
        return companyService.findByRegistryCode(registryCode);
    }

    @GetMapping("/{registryCode}/full")
    public Company findByRegistryCodeWithFullDetails(@PathVariable String registryCode) {
        return companyService.findByRegistryCodeWithFullDetails(registryCode);
    }

    @GetMapping("/all")
    public Iterable<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/all/{page}/{size}")
    public Page<CompanyDTO> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return companyService.findAllPaginated(page, size);
    }

    @GetMapping("/search/{text}")
    public Page<CompanyDTO> search(@PathVariable String text, @RequestParam Integer page, @RequestParam Integer size) {
        return companyService.search(text, page, size);
    }

    @GetMapping("/owner/{ownerId}")
    public List<CompanyDTO> findByOwnerId(@PathVariable String ownerId) {
        return companyService.findByOwnerId(ownerId);
    }

    @GetMapping("/director/{personId}")
    public List<CompanyDTO> findByDirectorId(@PathVariable String personId) {
        // Get other relations with companies besides ownership
        return companyService.findByDirectorId(personId);
    }
}
