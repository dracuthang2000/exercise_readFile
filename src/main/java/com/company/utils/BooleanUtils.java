package com.company.utils;

import org.apache.commons.lang3.StringUtils;

public class BooleanUtils {
    public static Boolean booleanParse(String text){
        if(StringUtils.isBlank(text)){
            return null;
        }
        return ("1".equals(text));
    }
}

