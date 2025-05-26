package com.ee_stats.initial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ee_stats.initial.dto.CompanyDTO;
import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyDTO findByRegistryCode(String registryCode) {
        Company company = companyRepository.findByRegistryCode(registryCode);
        if (company != null) {
            return convertToDTO(company);
        }
        return null;
    }

    public Company findByRegistryCodeWithFullDetails(String registryCode) {
        return companyRepository.findByRegistryCode(registryCode);
    }

    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    public Page<CompanyDTO> findAllPaginated(Integer page, Integer size) {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(page, size));
        return companies.map(this::convertToDTO);
    }

    public Page<CompanyDTO> search(String text, Integer page, Integer size) {
        Page<Company> companies = companyRepository.findByNameContainingIgnoreCase(text, PageRequest.of(page, size));
        return companies.map(this::convertToDTO);
    }

    private CompanyDTO convertToDTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getRegistryCode(),
                company.getName(),
                company.getType(),
                company.getBusinessArea(),
                company.getCounty(),
                company.getIsVat());
    }
}
