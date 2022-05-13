package com.company.readerFile;

import com.company.Object.Company;
import com.company.repo.CompanyRepository;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReadCSVFile extends ReaderFile {
    public List<Company> executeFile(BufferedReader bufferedReader) throws IOException {
        List<Company> companies = new ArrayList<>();
        Logger logger = Logger.getGlobal();
        String line;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {

            String[] listStringCompany = StringUtils.splitPreserveAllTokens(line, ",");
            try {
                Company cmp = new Company(Integer.parseInt(listStringCompany[0])
                        , listStringCompany[1]
                        , new CompanyRepository().dateParse(listStringCompany[2])
                        , Integer.parseInt(listStringCompany[3])
                        , listStringCompany[4]
                        , new CompanyRepository().booleanParse(listStringCompany[5]));
                companies.add(cmp);
            } catch (NullPointerException e) {
                logger.info("The value Error at id: " + listStringCompany[0]);
                e.printStackTrace();
            } catch (NumberFormatException e) {
                logger.info("The value Error at id: " + listStringCompany[0]);
                e.printStackTrace();
            }
        }
        return companies;
    }
}
