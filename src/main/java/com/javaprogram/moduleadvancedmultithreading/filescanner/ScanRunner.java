package com.javaprogram.moduleadvancedmultithreading.filescanner;

public class ScanRunner {
    public static final String PATH = "../src/main/java/com/javaprogram/moduleadvancedmultithreading/filescanner";

    public static void main(String[] args) {
        ScanAction.scan(PATH);
    }

}
