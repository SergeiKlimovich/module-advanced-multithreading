package com.javaprogram.moduleadvancedmultithreading.factorialviafjp;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Factorial extends RecursiveTask<BigInteger> {

    private final BigInteger value;

    @Override
    protected BigInteger compute() {
        if (value.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }

        Factorial factorial = new Factorial(value.subtract(BigInteger.ONE));
        factorial.fork();
        return value.multiply(factorial.join());

    }
}