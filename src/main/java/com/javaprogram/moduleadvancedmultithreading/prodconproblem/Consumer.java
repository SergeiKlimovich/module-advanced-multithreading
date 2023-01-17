package com.javaprogram.moduleadvancedmultithreading.prodconproblem;

import java.util.concurrent.BlockingQueue;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Consumer implements Runnable {

    private final BlockingQueue<Integer> questionQueue;

    @Override
    @SneakyThrows
    public void run() {
        Thread.sleep(1000);
        System.out.println("Was answered the question " + questionQueue.take());
    }

}