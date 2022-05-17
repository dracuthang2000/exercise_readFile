package com.company.services;

import com.company.entity.Company;
import com.company.global.AppGlobalCompany;
import com.company.reader.Reader;
import com.company.reader.ReaderAbstractFactory;
import com.company.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class CompanyServiceIpm implements CompanyService {
    // get list value from file csv.
    @Override
    public void getListCompanies(String fileName) {
            ReaderAbstractFactory abstractFactory = Reader.getFactory(FileUtils.fileFormat(fileName));
            if(abstractFactory!=null) {
                AppGlobalCompany.companies = abstractFactory.executeFile(fileName);
            }
    }

    // filter Country is in "CH" and sorted descending by Capital after filter
    @Override
    public List<Company> filterAndSortValue(List<Company> listCompanies, String country) {
        if(StringUtils.isBlank(country)){
            return Collections.emptyList();
        }
        return
                listCompanies.stream()
                        .filter(company -> country.equals(company.getCountry()))
                        .sorted((o1, o2) -> {
                            return o2.getCapital() - o1.getCapital();
                        })
                        .collect(Collectors.toList());
    }

    @Override
    public void showValueOfCountry(String countryName) {
        List<Company> listCompanies = new ArrayList<>();
        listCompanies = filterAndSortValue(AppGlobalCompany.companies, countryName);
        System.out.println("Total capital that the country is in CH: " + listCompanies.stream().count());
        System.out.println("The name of companies that the country is in CH: ");
        listCompanies.forEach(company -> System.out.println(company.toString() + ", "));
    }

    @Override
    public void showBigDataOfCompany(String zipFileName, String destDirectory) {
        FileService fileService = new FileServiceImp();
        getListCompanies(fileService.getFileNameByUnzip(zipFileName, destDirectory));
        AppGlobalCompany.companies.parallelStream().forEach(company -> System.out.println(company.toString()));
    }

}
