package com.company.services;

import com.company.Object.Company;
import com.company.readerFile.ReadCSVFile;
import com.company.readerFile.ReaderFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ServiceCompanyIpm implements SeviceCompany {

    // get list value from file csv.
    @Override
    public List<Company> getListCompanies(String fileName) {
        List<Company> listCompanies = new ArrayList<>();
        try {
            listCompanies = new ReadCSVFile().load(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCompanies;
    }
    // filter Country is in "CH" and sorted descending by Capital after filter
    @Override
    public List<Company> filterSortValue(List<Company> listCompanies,String country) {
        List<Company> filter =
                listCompanies.stream()
                        .filter(company -> country.equals(company.getCountry()))
                        .sorted((o1, o2) -> {
                            return o2.getCapital() - o1.getCapital();
                        })
                        .collect(Collectors.toList());
        return filter;
    }


    // Got file name after unzip
    @Override
    public String getFileNameByUnzip(String zipFileName, String destDirectory) {
        String getFile = "";
        try {
            File destDirectoryFolder = new File(destDirectory);
            if (!destDirectoryFolder.exists()) {
                destDirectoryFolder.mkdir();
            }
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String filePath = destDirectory + File.separator + zipEntry.getName();
                getFile = filePath;
                if (!zipEntry.isDirectory()) {
                    try {
                        FileOutputStream fos = new FileOutputStream(filePath);
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                        fos.close();
                    }catch (IOException e){
                        e.printStackTrace();
                        zis.close();
                    }
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            return getFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void showValueOfCountry(String fileName,String filterCountry) {
        List<Company> listCompanies = new ArrayList<>();
        ServiceCompanyIpm serviceCompanyIpm = new ServiceCompanyIpm();
        listCompanies = serviceCompanyIpm.filterSortValue(serviceCompanyIpm.getListCompanies(fileName),"CH");
        System.out.println("Total capital that the country is in CH: " + listCompanies.stream().count());
        System.out.println("The name of companies that the country is in CH: ");
        listCompanies.forEach(company -> System.out.println(company.toString() + ", "));
    }

    @Override
    public void unzip(String zipFileName, String destDirectory) {
        List<Company> listCompanies = new ArrayList<>();
        ServiceCompanyIpm serviceCSV = new ServiceCompanyIpm();
        listCompanies = serviceCSV.getListCompanies(serviceCSV.getFileNameByUnzip(zipFileName, destDirectory));
        listCompanies.parallelStream().forEach(company -> System.out.println(company.getName()));
    }
}
