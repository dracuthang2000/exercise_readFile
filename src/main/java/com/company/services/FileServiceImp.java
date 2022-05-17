package com.company.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileServiceImp implements FileService {
    @Override
    public String getFileNameByUnzip(String zipFileName, String destDirectory){
        String getFile = "";
        FileOutputStream fos = null;
        File destDirectoryFolder = new File(destDirectory);
        ZipInputStream zis = null;
        try {
            if (!destDirectoryFolder.exists()) {
                destDirectoryFolder.mkdir();
            }
            byte[] buffer = new byte[1024];
            zis = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String filePath = destDirectory + File.separator + zipEntry.getName();
                getFile = filePath;
                if (!zipEntry.isDirectory()) {
                    fos = new FileOutputStream(filePath);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            return getFile;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if(zis!=null){
                    zis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
