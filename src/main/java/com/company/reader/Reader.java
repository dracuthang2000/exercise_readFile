package com.company.reader;

import org.apache.commons.lang3.StringUtils;

public class Reader {
    private Reader() {

    }
    public static ReaderAbstractFactory getFactory(String fileFormat) {
        if(StringUtils.isBlank(fileFormat)){
            return null;
        }else {
            if (fileFormat.equals(FileFormatEnum.CSV.toString())) {
                return new ReadCSV();
            } else if (fileFormat.equals(FileFormatEnum.XML.toString())) {
                return null;
            }
        }
        return null;
    }
}
