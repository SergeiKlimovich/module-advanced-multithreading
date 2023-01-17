package com.javaprogram.moduleadvancedmultithreading.sumofsquare;

import java.util.concurrent.RecursiveAction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Applyer extends RecursiveAction {

    private final Double[] array;
    private final int low;
    private final int high;
    double result;
    private final Applyer next;

    @Override
    protected void compute() {
        int l = low;
        int h = high;
        Applyer right = null;
        while (h - l > 1 && getSurplusQueuedTaskCount() <= 3) {
            int mid = (l + h) >>> 1;
            right = new Applyer(array, mid, h, right);
            right.fork();
            h = mid;
        }
        double sum = atLeaf(l, h);
        while (right != null) {
            if (right.tryUnfork()) {
                sum += right.atLeaf(right.low, right.high);
            } else {
                right.join();
                sum += right.result;
            }
            right = right.next;
        }
        result = sum;
    }

    double atLeaf(int l, int h) {
        double sum = 0;
        for (int i = l; i < h; ++i) {
            sum += array[i] * array[i];
        }
        return sum;
    }
}
