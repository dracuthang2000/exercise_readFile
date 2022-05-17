package com.company.reader;

import com.company.entity.Company;
import com.company.utils.BooleanUtils;
import com.company.utils.LocalDateUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ReadCSV extends ReaderAbstractFactory {
    public List<Company> executeFile(String fileName){
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
            List<Company> companies = new ArrayList<>();
            Logger logger = Logger.getGlobal();
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {

                String[] listStringCompany = StringUtils.splitPreserveAllTokens(line, ",");
                try {
                    Company cmp = new Company(
                            Integer.parseInt(listStringCompany[0])
                            , listStringCompany[1]
                            , LocalDateUtils.localDateParse(listStringCompany[2])
                            , Integer.parseInt(listStringCompany[3])
                            , listStringCompany[4]
                            , BooleanUtils.booleanParse(listStringCompany[5]));
                    companies.add(cmp);
                } catch (NullPointerException | NumberFormatException e) {
                    logger.info("The value Error at id: " + listStringCompany[0]);
                    e.printStackTrace();
                }
            }
            return companies;
        }catch (IOException e){
            e.printStackTrace();
            return Collections.emptyList();
        }finally {

                try {
                    if(bufferedReader!=null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}



