package com.javaprogram.moduleadvancedmultithreading.prodconproblem;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Application {
    public static final Integer CAPACITY = 10;

    public static void main(String[] args) {
        BlockingQueue<Integer> questionQueue = new ArrayBlockingQueue<>(CAPACITY);
        Thread producer = new Thread(new Producer(questionQueue));
        Thread consumer = new Thread(new Consumer(questionQueue));

        producer.start();
        consumer.start();
    }

}
