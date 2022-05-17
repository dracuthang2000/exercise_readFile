package com.company.thread;

import com.company.services.CompanyService;
import com.company.services.CompanyServiceIpm;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class ThreadWatchingChanged extends Thread {
    private String destDirectory;
    private String fileName;
    private CompanyService companyService;
    private volatile Boolean exit = false;

    public ThreadWatchingChanged(String destDirectory, String fileName) {
        this.destDirectory = destDirectory;
        this.fileName = fileName;
    }

    public ThreadWatchingChanged() {
    }

    public String getDestDirectory() {
        return destDirectory;
    }

    public void setDestDirectory(String destDirectory) {
        this.destDirectory = destDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Watch event when changed file csv and print it
    @Override
    public void run() {
        companyService = new CompanyServiceIpm();
        while(true) {
            System.out.println("The watching changed is processing......!");
            if (fileWatchChanged(destDirectory, fileName)) {
                companyService.getListCompanies(destDirectory + "\\" + fileName);
                showValue(companyService);
            }
        }
    }
    private Boolean fileWatchChanged(String destDirectory,String fileName){
        try (WatchService service = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path dir = Paths.get(destDirectory);
            keyMap.put(dir.register(service,
                    StandardWatchEventKinds.ENTRY_MODIFY),
                    dir);
            WatchKey watchKey;
            do {
                watchKey = service.take();
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    Path eventPath = (Path) event.context();
                    if(eventPath.endsWith(fileName)){
                        return true;
                    }
                }
            } while (watchKey.reset());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    private void showValue(CompanyService companyService){
        companyService.showValueOfCountry("CH");
    }
}
