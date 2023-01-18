package com.javaprogram.moduleadvancedmultithreading.filescanner;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Stream;

import dnl.utils.text.table.TextTable;

public class ScanAction extends RecursiveAction {

    public static final String KILOBYTES = " KB";
    public static final String FILES = "Files";
    public static final String FOLDERS = "Folders";
    public static final String SIZE = "Size";

    private final Map<String, Long> scannedFiles;
    private final Map<String, Long> scannedFolders;
    private final String directoryPath;
    private int fileCount = 0;
    private int folderCount = 0;

    public ScanAction(String directoryPath) {
        this.directoryPath = directoryPath;
        this.scannedFiles = new ConcurrentHashMap<>();
        this.scannedFolders = new ConcurrentHashMap<>();
    }

    public static void scan(String directoryPath) {
        ForkJoinTask.invokeAll(new ScanAction(directoryPath));
    }

    @Override
    protected void compute() {
        File file = new File(directoryPath);
        if (file.listFiles() != null) {
            Stream.of(file.listFiles())
                    .forEach(file1 -> {
                        if (file1.isDirectory()) {
                            folderCount++;
                            scannedFolders.put(
                                    file1.getName(),
                                    ForkJoinTask.invokeAll(
                                                    List.of(new ScanFolder(file1)))
                                            .stream()
                                            .map(ForkJoinTask::join)
                                            .reduce(0L, Long::sum)
                            );
                        } else {
                            fileCount++;
                            scannedFiles.put(file1.getName(), file1.length());
                        }
                    });
            print();
        }
    }

    private void print() {
        printTable(scannedFiles, List.of(FILES, SIZE));
        printTable(scannedFolders, List.of(FOLDERS, SIZE));

    }

    private void printTable(Map<String, Long> datas, List<String> names) {
        String[][] data = datas.entrySet().stream()
                .map(e -> List.of(e.getKey(), (double) e.getValue() / 1024 + KILOBYTES).toArray(new String[] {}))
                .toList()
                .toArray(new String[][] {});
        TextTable textTable = new TextTable(names.toArray(new String[] {}), data);
        textTable.printTable();
    }

}
