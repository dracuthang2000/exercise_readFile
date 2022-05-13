package com.company.repo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompanyRepository {
    public Boolean booleanParse(String text){
        if("".equals(text)){
            return null;
        }else if("1".equals(text)){
            return true;
        }else {
            return false;
        }
    }
    public LocalDate dateParse(String text){
        LocalDate localDate =LocalDate.parse("1/1/1990",DateTimeFormatter.ofPattern("M/d/yyyy"));
        if(text.length()==4){
            localDate = LocalDate.parse("1/1/"+text,DateTimeFormatter.ofPattern("M/d/yyyy"));
        }else {
            localDate = LocalDate.parse(text,DateTimeFormatter.ofPattern("M/d/yyyy"));
        }
        return localDate;
    }
}
