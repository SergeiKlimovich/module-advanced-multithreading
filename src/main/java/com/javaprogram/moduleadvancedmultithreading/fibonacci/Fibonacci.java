package com.javaprogram.moduleadvancedmultithreading.fibonacci;

import java.util.concurrent.RecursiveTask;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Fibonacci extends RecursiveTask<Integer> {

    private final int number;

    @Override
    protected Integer compute() {
        if (number <= 10) {
            return calculateLinearly(number);
        }

        Fibonacci fibonacciFirst = new Fibonacci(number - 1);
        fibonacciFirst.fork();
        Fibonacci fibonacciSecond = new Fibonacci(number - 2);
        return fibonacciSecond.compute() + fibonacciFirst.join();
    }

    private Integer calculateLinearly(Integer integer) {
        if (integer <= 1) {
            return integer;
        }

        int previous = 0;
        int current = 1;
        int next = 0;

        for (int i = 1; i < integer; i++) {
            next = previous + current;
            previous = current;
            current = next;
        }

        return next;
    }

}
