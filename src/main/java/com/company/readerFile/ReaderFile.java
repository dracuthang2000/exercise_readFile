package com.company.readerFile;

import com.company.Object.Company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ReaderFile {
    abstract List<Company> executeFile(BufferedReader bufferedReader) throws IOException;
    public List<Company> load(String fileName) throws IOException {
        List<Company> companies = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        if(fileName.endsWith("csv")){
            companies = new ReadCSVFile().executeFile(bufferedReader);
            bufferedReader.close();
            return companies;
        }else if (fileName.endsWith("xml")){
            bufferedReader.close();
            return companies;
        }
        bufferedReader.close();
        return companies;
    }
}
