package com.company.threadWatchingChanged;
import com.company.Object.Company;
import com.company.services.ServiceCompanyIpm;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadWatchingChanged extends Thread {
    private String destDirectory;
    private String fileName;

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
        fileWatchChanged(destDirectory,fileName);
    }

    public void fileWatchChanged(String destDirectory,String fileName){
        ServiceCompanyIpm serviceCompanyIpmCmp = new ServiceCompanyIpm();
        List<Company> listCompanies = new ArrayList<>();
        try (WatchService service = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path dir = Paths.get(destDirectory);
            keyMap.put(dir.register(service,
                    StandardWatchEventKinds.ENTRY_MODIFY),
                    dir);
            WatchKey watchKey;

            do {
                watchKey = service.take();
                Path eventDir = keyMap.get(watchKey);
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    if(eventPath.endsWith(fileName)){
                        listCompanies = serviceCompanyIpmCmp.filterSortValue(serviceCompanyIpmCmp.getListCompanies(destDirectory+"\\"+fileName),"CH");
                        System.out.println("Total capital that the country is in CH: " + listCompanies.stream().count());
                        System.out.println("The name of companies that the country is in CH: ");
                        listCompanies.forEach(company -> System.out.print(company.getName()+", "));
                        System.out.println();
                    }
                }
            } while (watchKey.reset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
