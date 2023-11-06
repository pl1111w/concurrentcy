package com.pl1111w.concurrent.example.future.completable.helloworld;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2023/11/2 23:08
 */
public class CompletableFutureCompletionStage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //默认ForkJoinPool 线程池 它守护线程 所以要main sleep >执行时间
        CompletableFuture<Integer> noFeedbackFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((result,e)->{
            if(e==null){
                System.out.println(result);
            }

        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return null;
        });
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+" do other work");
    }
}
