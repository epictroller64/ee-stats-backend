package com.ee_stats.initial.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.repositories.CompanyRepository;
import com.ee_stats.initial.services.ExcelService;

@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final ExcelService excelService;

    public CompanyController(CompanyRepository companyRepository, ExcelService excelService) {
        this.companyRepository = companyRepository;
        this.excelService = excelService;
    }

    @GetMapping("/companies")
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @PostMapping("/companies")
    public ResponseEntity<List<Company>> importCompanies(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        List<Company> companies = excelService.processInputFile(file);
        if (companies == null) {
            return ResponseEntity.badRequest().build();
        }
        companyRepository.saveAll(companies);
        return ResponseEntity.ok(companies);
    }

}
