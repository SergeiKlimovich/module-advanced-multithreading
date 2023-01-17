package com.javaprogram.moduleadvancedmultithreading.mergesort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MergeSortAction extends RecursiveAction {
    private final int[] array;
    private final int leftElement;
    private final int rightElement;

    public MergeSortAction(int[] array) {
        this(array, 0, array.length - 1);
    }

    @Override
    protected void compute() {
        ForkJoinTask.invokeAll(createSubActions());
    }

    private List<RecursiveAction> createSubActions() {
        if (leftElement < rightElement) {
            int middleElement = (leftElement + rightElement) / 2;

            return Arrays.asList(new MergeSortAction(array, leftElement, middleElement),
                    new MergeSortAction(array, middleElement + 1, rightElement),
                    new MergeAction(array, leftElement, middleElement, rightElement));
        }
        return Collections.emptyList();
    }

}
