package com.ee_stats.initial.controllers;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.models.CompanyHistory;
import com.ee_stats.initial.repositories.CompanyRepository;
import com.ee_stats.initial.services.ExcelService;
import com.ee_stats.initial.services.HistoryService;

@RestController
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ExcelService excelService;

    @Autowired
    private HistoryService historyService;

    // Pull all quarters for a company by registry code. After calculation the
    // result will be inserted into the database
    @GetMapping("/companies/history/{registryCode}")
    public CompanyHistory getHistoryByRegistryCode(@PathVariable String registryCode) {
        return historyService.getHistoryByRegistryCode(registryCode);
    }

    @GetMapping("/companies/{id}")
    public Company findById(@PathVariable Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    @GetMapping("/companies")
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @PostMapping("/companies")
    public DeferredResult<ResponseEntity<List<Company>>> importCompanies(MultipartFile file,
            RedirectAttributes redirectAttributes) {
        DeferredResult<ResponseEntity<List<Company>>> deferredResult = new DeferredResult<>(0L);

        deferredResult.onError((Throwable t) -> {
            if (t instanceof AsyncRequestTimeoutException) {
                deferredResult
                        .setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout"));
            } else {
                deferredResult.setErrorResult(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error"));
            }
        });
        if (file == null) {
            deferredResult.setErrorResult(ResponseEntity.badRequest().body("File is null"));
            return deferredResult;
        }
        if (file.isEmpty()) {
            deferredResult.setErrorResult(ResponseEntity.badRequest().body("Invalid file"));
            return deferredResult;
        }
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().endsWith(".xlsx")) {
            deferredResult.setErrorResult(ResponseEntity.badRequest().body("Invalid file"));
            return deferredResult;
        }
        ForkJoinPool.commonPool().submit(() -> {
            try {

                List<Company> companies = excelService.processInputFile(file);
                if (companies == null) {
                    deferredResult.setErrorResult(ResponseEntity.badRequest().build());
                } else {
                    deferredResult.setResult(ResponseEntity.ok().build());
                }
            } catch (Exception e) {
                deferredResult
                        .setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
            }
        });

        return deferredResult;
    }

}
