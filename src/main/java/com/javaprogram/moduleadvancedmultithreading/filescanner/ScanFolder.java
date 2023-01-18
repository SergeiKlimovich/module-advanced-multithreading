package com.javaprogram.moduleadvancedmultithreading.filescanner;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class ScanFolder extends RecursiveTask<Long> {
    private final File file;

    public ScanFolder(File file) {
        this.file = file;
    }

    @Override
    protected Long compute() {
        if (file.listFiles() != null) {
            return Stream.of(file.listFiles()).mapToLong(file -> {
                if (file.isDirectory()) {
                    return ForkJoinTask.invokeAll(List.of(new ScanFolder(file)))
                                    .stream()
                                    .map(ForkJoinTask::join)
                                    .reduce(0L, Long::sum);
                } else {
                    return file.length();
                }
            })
                    .reduce(0L, Long::sum);
        }
        return 0L;
    }

}
