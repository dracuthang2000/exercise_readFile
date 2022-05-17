package com.company.services;
import com.company.entity.Company;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    void getListCompanies(String fileName);
    List<Company> filterAndSortValue(List<Company> listCompanies,String country);
    void showValueOfCountry(String filterCountry);
    void showBigDataOfCompany(String zipFileName, String destDirectory);
}
