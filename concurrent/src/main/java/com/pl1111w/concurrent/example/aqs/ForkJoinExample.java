package com.pl1111w.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {

    private static final int threadHold = 2;
    private int begin;
    private int end;

    public ForkJoinExample(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinExample forkJoinExample = new ForkJoinExample(1, 100);
        Future<Integer> result = forkJoinPool.submit(forkJoinExample);
        try {
            log.info("result:{}", result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - begin) <= threadHold;
        if (canCompute) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (begin + end) / 2;
            ForkJoinExample forkJoinExampleLeft = new ForkJoinExample(begin, middle);
            ForkJoinExample forkJoinExampleRight = new ForkJoinExample(middle + 1, end);
            forkJoinExampleLeft.fork();
            forkJoinExampleRight.fork();
            int leftResult = forkJoinExampleLeft.join();
            int rightResult = forkJoinExampleRight.join();
            sum = rightResult + leftResult;
        }
        return sum;
    }
}
