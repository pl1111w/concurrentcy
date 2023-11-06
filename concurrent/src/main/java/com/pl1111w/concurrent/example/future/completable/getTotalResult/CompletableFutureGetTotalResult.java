package com.pl1111w.concurrent.example.future.completable.getTotalResult;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: get total result
 * @author: Kris
 * @date 2023/11/2 23:08
 */
public class CompletableFutureGetTotalResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //默认ForkJoinPool 线程池 它守护线程 所以要main sleep >执行时间
        CompletableFuture<Integer> noFeedbackFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println(result);
            }

        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

        CompletableFuture<Integer> noFeedbackFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println(result);
            }

        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

        CompletableFuture<Integer> noFeedbackFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println(result);
            }

        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

        CompletableFuture<Integer> combine = noFeedbackFuture.thenCombine(noFeedbackFuture2, (x, y) -> {
            return x + y;
        }).thenCombine(noFeedbackFuture3, (x, y) -> {
            return x + y;
        });
        System.out.println(combine.join());
    }
}
