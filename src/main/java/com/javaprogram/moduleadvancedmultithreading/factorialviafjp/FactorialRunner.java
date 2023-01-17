package com.javaprogram.moduleadvancedmultithreading.factorialviafjp;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class FactorialRunner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long integer = 7L;
        ForkJoinTask<BigInteger> forkJoinTask = new Factorial(BigInteger.valueOf(integer));
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(forkJoinTask);
        System.out.println("Factorial of " + integer + " is equal " + forkJoinTask.get());

    }
}
