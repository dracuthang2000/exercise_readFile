package com.company;

import java.util.Comparator;

public class SortByCapital implements Comparator<Company> {

    @Override
    public int compare(Company o1, Company o2) {
        return o1.getCapital()-o2.getCapital();
    }
}
