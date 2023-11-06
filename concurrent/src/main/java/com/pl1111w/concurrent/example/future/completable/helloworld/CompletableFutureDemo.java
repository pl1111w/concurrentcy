package com.pl1111w.concurrent.example.future.completable.helloworld;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description:  CompletableFuture 四大静态方法
 * @author: Kris
 * @date 2023/10/31 10:36
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //默认ForkJoinPool 线程池
        CompletableFuture<Void> noFeedbackFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"---come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(noFeedbackFuture.get());
        //指定线程池
        ExecutorService threadPool= Executors.newCachedThreadPool();
        CompletableFuture<Void> noFeedbackFuture2 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"---come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },threadPool);
        System.out.println(noFeedbackFuture2.get());
        //指定线程池
        CompletableFuture<String> backFuture3 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"---come in");
            try {
                TimeUnit.SECONDS.sleep(1);
                return "hello";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        },threadPool);
        System.out.println(backFuture3.get());

        //默认ForkJoinPool 线程池
        CompletableFuture<String> backFuture4 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"---come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "你好";
        });
        System.out.println(backFuture4.get());


    }
}
