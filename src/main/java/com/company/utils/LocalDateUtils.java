package com.company.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {
    public static LocalDate localDateParse(String text){
        if(StringUtils.isBlank(text)){
            return null;
        }
        LocalDate localDate =LocalDate.parse("1/1/1990", DateTimeFormatter.ofPattern("M/d/yyyy"));
        if(text.length()==4){
            localDate = LocalDate.parse("1/1/"+text,DateTimeFormatter.ofPattern("M/d/yyyy"));
        }else {
            localDate = LocalDate.parse(text,DateTimeFormatter.ofPattern("M/d/yyyy"));
        }
        return localDate;
    }
}
