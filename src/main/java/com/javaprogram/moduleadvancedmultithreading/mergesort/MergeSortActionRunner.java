package com.javaprogram.moduleadvancedmultithreading.mergesort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;

public class MergeSortActionRunner {
    public static void main(String[] args) {
        int[] array = new int[] {23, 2, 100, 67, 12, 88, 7, 99, 19};

        System.out.println("original array - " + Arrays.toString(array));
        ForkJoinTask.invokeAll(new MergeSortAction(array));

        System.out.println("sorted array -   " + Arrays.toString(array));

    }
}
