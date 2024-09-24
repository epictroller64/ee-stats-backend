package com.ee_stats.initial.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
            if (filename.endsWith(".xlsx")) {
                companies = processXlsxDocumentFile(file);
            }
            if (companies == null) {
                throw new Exception("Companies list is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return companies;
    }

    private List<Company> processXlsxDocumentFile(MultipartFile file) throws Exception {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            String filename = file.getOriginalFilename();

            XSSFSheet sheet = workbook.getSheetAt(0);

            List<Company> companies = new ArrayList<>();

            year = getYearFromName(filename);
            quarter = getQuarterFromName(filename);

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                if (i == 0) {
                    continue;
                }
                XSSFRow row = sheet.getRow(i);
                Company company = getCompanyFromXLSXRow(row);
                if (company != null) {
                    companies.add(company);
                }
            }

            int batchSize = 30; // Adjust the batch size as needed
            for (int i = 0; i < companies.size(); i += batchSize) {
                int end = Math.min(i + batchSize, companies.size());
                List<Company> batchList = companies.subList(i, end);
                companyRepository.saveAll(batchList);
            }
            return companies;
        }
    }

    private Integer romanToArabic(String roman) {
        switch (roman.toUpperCase()) {
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

    private Company getCompanyFromXLSXRow(XSSFRow row) {
        String registeredVatText = row.getCell(3).getStringCellValue();
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
                getBigDecimalValueOrDefault(row, 6, BigDecimal.ZERO),
                getBigDecimalValueOrDefault(row, 7, BigDecimal.ZERO),
                getBigDecimalValueOrDefault(row, 8, BigDecimal.ZERO),
                getIntegerValueOrDefault(row, 9, 0),
                quarter,
                year);

    }

    private BigDecimal getBigDecimalValueOrDefault(XSSFRow row, int index, BigDecimal defaultValue) {
        try {
            double value = row.getCell(index).getNumericCellValue();
            return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private Boolean isRowEmpty(XSSFRow row) {
        return row.getCell(0).getStringCellValue().isEmpty();
    }

    private String getStringValueOrDefault(XSSFRow row, int index, String defaultValue) {
        String value = row.getCell(index).getStringCellValue();
        return value != null && !value.trim().isEmpty() ? value : defaultValue;
    }

    private Integer getIntegerValueOrDefault(XSSFRow row, int index, Integer defaultValue) {
        try {
            return (int) row.getCell(index).getNumericCellValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
