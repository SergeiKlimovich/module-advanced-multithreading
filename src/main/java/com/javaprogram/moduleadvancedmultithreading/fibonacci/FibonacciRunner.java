package com.javaprogram.moduleadvancedmultithreading.fibonacci;

import java.util.concurrent.ForkJoinPool;

public class FibonacciRunner {
    public static void main(String[] args) {
        Fibonacci task = new Fibonacci(25);
        int result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }

}
