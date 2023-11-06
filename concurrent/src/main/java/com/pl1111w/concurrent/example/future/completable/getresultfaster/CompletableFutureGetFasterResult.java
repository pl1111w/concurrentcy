package com.pl1111w.concurrent.example.future.completable.getresultfaster;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: get final result
 * @author: Kris
 * @date 2023/11/2 23:08
 */
public class CompletableFutureGetFasterResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //默认ForkJoinPool 线程池 它守护线程 所以要main sleep >执行时间
        CompletableFuture<String> noFeedbackFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            try {
                TimeUnit.SECONDS.sleep(4);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "A is winner";
        });
        CompletableFuture<String> noFeedbackFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "B is winner";
        });
        CompletableFuture<String> noFeedbackFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            try {
                TimeUnit.SECONDS.sleep(2);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C is winner";
        });
        CompletableFuture<String> completableFuture = noFeedbackFuture.applyToEither(noFeedbackFuture2, f -> {
            return f;
        }).applyToEither(noFeedbackFuture3, f -> {
            return f;
        });

        System.out.println("result: " + completableFuture.join());
    }
}
