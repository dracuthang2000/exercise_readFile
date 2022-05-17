package com.company.utils;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {
    public static String fileFormat(String fileName){
        int lastIndexOf = regexFile(fileName,".");
        return value(lastIndexOf,fileName.substring(lastIndexOf));
    }

    public static String getFileName(String file){
        int lastIndexOf = regexFile(file,"\\");
        return value(lastIndexOf,file.substring(lastIndexOf+1));
    }

    public static String getURL(String file){
        int lastIndexOf = regexFile(file,"\\");
        return value(lastIndexOf,file.substring(0,lastIndexOf-1));
    }
    private static int regexFile(String file,String regex){
        if(StringUtils.isBlank(file)){
            return -1;
        }
        int lastIndexOf = file.lastIndexOf(regex);
        return lastIndexOf;
    }
    private static String value(int index,String textReturn){
        if(index==-1) {
            return "";
        }
        return textReturn;
    }

}
