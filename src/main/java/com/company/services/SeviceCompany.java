package com.company.services;
import com.company.Object.Company;

import java.util.List;

public interface SeviceCompany {
    List<Company>getListCompanies(String fileName);
    List<Company> filterSortValue(List<Company> listCompanies,String country);
    String getFileNameByUnzip(String zipFileName, String destDirectory);
    void showValueOfCountry(String fileName,String filterCountry);
    void unzip(String zipFileName, String destDirectory);
}
