package com.javaprogram.moduleadvancedmultithreading.future;

import com.javaprogram.moduleadvancedmultithreading.future.service.EmployeeService;
import com.javaprogram.moduleadvancedmultithreading.future.service.PrintService;

public class FutureRunner {
    public static void main(String[] args) {
        PrintService printService = new PrintService(new EmployeeService());
        printService.printEmployeesSalary();
    }

}
