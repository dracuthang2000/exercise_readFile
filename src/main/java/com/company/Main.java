package com.company;

import com.company.services.CompanyServiceIpm;
import com.company.services.CompanyService;
import com.company.thread.ThreadWatchingChanged;
import com.company.utils.FileUtils;

import java.util.*;


public class Main {
    static ThreadWatchingChanged threadWatchingChanged;
    static Boolean flag = true;
    public static void display(int index,String url, String fileName, CompanyService companyServiceIpm) {
        switch (index) {
            case 1:
                companyServiceIpm.showValueOfCountry("CH");
                break;
            case 2:
                threadWatchingChanged = new ThreadWatchingChanged(url, fileName);
                threadWatchingChanged.start();
                break;
            case 3:
                    companyServiceIpm.showBigDataOfCompany("C:\\Users\\NGUG\\Desktop\\companies_big_data.zip", "C:\\Users\\NGUG\\Desktop\\output");
                    break;
            default: return;
        }
    }

    // "C:\\Users\\NGUG\\Desktop\\test\\file_test.csv"
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CompanyService companyServiceIpm = new CompanyServiceIpm();
        companyServiceIpm.getListCompanies(args[0]);
        int index;
        System.out.println("1. Show data file CSV total and filter by name of company.");
        System.out.println("2. Show data file CSV total and filter by name of company when watching changed.");
        System.out.println("3. Show data file CSV from file big data.");
        System.out.println("0. Exit");
        String url = FileUtils.getURL(args[0]);
        String fileName = FileUtils.getFileName(args[0]);
        do {
            System.out.print("index: ");
            index = sc.nextInt();
            display(index,url,fileName, companyServiceIpm);
        } while (index != 0);
    }
}
