package com.ee_stats.initial.services;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ee_stats.initial.models.Company;
import com.ee_stats.initial.repositories.CompanyRepository;

@Service
public class ExcelService {

    @Autowired
    private CompanyRepository companyRepository;
    private Integer year;
    private Integer quarter;

    public List<Company> processInputFile(MultipartFile file) {
        List<Company> companies = null;
        try {
            String filename = file.getOriginalFilename();
            if (filename == null) {
                throw new Exception("Filename is null");
            }
            if (filename.endsWith(".ods")) {
                companies = processOpenDocumentFile(file);
            }
            if (companies == null) {
                throw new Exception("Companies list is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return companies;
    }

    private List<Company> processOpenDocumentFile(MultipartFile file) throws Exception {
        try (OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(file.getInputStream())) {
            String filename = file.getOriginalFilename();
            OdfTable sheet = spreadsheet.getTableList(false).get(0);
            List<OdfTableRow> rows = sheet.getRowList();

            // Skip header
            if (rows.size() > 0) {
                rows.remove(0);
            }

            List<Company> companies = new ArrayList<>();

            year = getYearFromName(filename);
            quarter = getQuarterFromName(filename);

            for (OdfTableRow row : rows) {
                Company company = getCompanyFromODSRow(row);
                if (company != null) {
                    companies.add(company);
                } else {
                    System.err.println("Company is null");
                }
            }

            return companies;
        }

    }

    private Integer romanToArabic(String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            default:
                return 0;
        }
    }

    private Integer getQuarterFromName(String name) {
        String[] parts = name.split("_");
        String kvartalRoman = parts[3];
        return romanToArabic(kvartalRoman);
    }

    private Integer getYearFromName(String name) {
        String[] parts = name.split("_");
        return Integer.parseInt(parts[2]);
    }

    private Company getCompanyFromODSRow(OdfTableRow row) {
        String registeredVatText = row.getCellByIndex(3).getStringValue();
        Boolean registeredVat = registeredVatText.equals("jah");
        if (isRowEmpty(row)) {
            return null;
        }
        return new Company(
                getStringValueOrDefault(row, 0, ""),
                getStringValueOrDefault(row, 1, ""),
                getStringValueOrDefault(row, 2, ""),
                registeredVat,
                getStringValueOrDefault(row, 4, ""),
                getStringValueOrDefault(row, 5, ""),
                getFloatValueOrDefault(row, 6, 0f),
                getFloatValueOrDefault(row, 7, 0f),
                getFloatValueOrDefault(row, 8, 0f),
                getFloatValueOrDefault(row, 9, 0f).intValue(),
                quarter,
                year);

    }

    private Boolean isRowEmpty(OdfTableRow row) {
        return row.getCellByIndex(0).getStringValue().isEmpty();
    }

    private String getStringValueOrDefault(OdfTableRow row, int index, String defaultValue) {
        String value = row.getCellByIndex(index).getStringValue();
        return value != null && !value.trim().isEmpty() ? value : defaultValue;
    }

    private Float getFloatValueOrDefault(OdfTableRow row, int index, Float defaultValue) {
        try {
            return row.getCellByIndex(index).getDoubleValue().floatValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
