package com.company.services;

import java.io.IOException;

interface FileService {
    String getFileNameByUnzip(String zipFileName, String destDirectory);
}
