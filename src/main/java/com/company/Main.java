package com.company;

import com.company.services.ServiceCompanyIpm;
import com.company.threadWatchingChanged.ThreadWatchingChanged;

import java.util.*;


public class Main {
    public static void main(int index){
        ServiceCompanyIpm services = new ServiceCompanyIpm();
        switch (index){
            case 1:
                services.showValueOfCountry("C:\\Users\\NGUG\\Desktop\\test\\file_test.csv","CH");
                break;
            case 2:
                ThreadWatchingChanged threadWatchingChanged = new ThreadWatchingChanged("C:\\Users\\NGUG\\Desktop\\test", "file_test.csv");
                threadWatchingChanged.start();
                break;
            case 3:
                services.unzip("C:\\Users\\NGUG\\Desktop\\companies_big_data.zip", "C:\\Users\\NGUG\\Desktop\\output");
                break;
            default: return;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index;
        System.out.println("1. Show data file CSV total and filter by name of company.");
        System.out.println("2. Show data file CSV total and filter by name of company when watching changed.");
        System.out.println("3. Show data file CSV from file big data.");
        System.out.println("0. Exit");
        do {
            System.out.print("index: ");
            index = sc.nextInt();
            main(index);
        }while (index!=0);
    }
}
