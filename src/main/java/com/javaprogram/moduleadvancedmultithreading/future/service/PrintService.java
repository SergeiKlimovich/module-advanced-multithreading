package com.javaprogram.moduleadvancedmultithreading.future.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.javaprogram.moduleadvancedmultithreading.future.model.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintService {

    private final EmployeeService service;

    public void printEmployeesSalary() {
        CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return service.getEmployees().get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .thenApplyAsync(employees -> employees.stream()
                        .peek(employee -> {
                            try {
                                employee.setSalary(service.getSalary(employee.getId())
                                        .get());
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        })
                ).thenAccept(employees -> employees.forEach(this::printEmployeeSalary));
    }

    private void printEmployeeSalary(Employee employee) {
        System.out.println(
                employee.getId() + ". " + employee.getFirstName() + " " + employee.getLastName() + " makes "
                        + employee.getSalary() + "PLN");
    }

}
