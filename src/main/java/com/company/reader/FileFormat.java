package com.company.reader;

enum FileFormatEnum {
    XML(".xml"),
    CSV(".csv");

    String fileFormat;

    FileFormatEnum(final String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return fileFormat;
    }
}
