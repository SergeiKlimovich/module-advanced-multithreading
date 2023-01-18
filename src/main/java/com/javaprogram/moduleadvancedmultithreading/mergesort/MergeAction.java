package com.javaprogram.moduleadvancedmultithreading.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MergeAction extends RecursiveAction {

    private final int[] array;
    private final int leftElement;
    private final int middleElement;
    private final int rightElement;

    @Override
    protected void compute() {
        int[] leftPart = Arrays.copyOfRange(array, leftElement, middleElement + 1);
        int[] rightPart = Arrays.copyOfRange(array, middleElement + 1, rightElement + 1);

        int i = 0;
        int j = 0;
        int index = leftElement;
        while (i < leftPart.length && j < rightPart.length) {
            if (leftPart[i] < rightPart[j]) {
                array[index] = leftPart[i];
                i++;
            } else {
                array[index] = rightPart[j];
                j++;
            }
            index++;
        }

        while (i < leftPart.length) {
            array[index] = leftPart[i];
            i++;
        }

        while (j < rightPart.length) {
            array[index] = rightPart[j];
            j++;
        }
    }

}
