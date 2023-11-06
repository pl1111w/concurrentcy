package com.pl1111w.concurrent.example.future.completable.consumepreviousresult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @title: pl1111w
 * @description: 对计算结果进行处理
 * @author: Kris
 * @date 2023/11/5 22:54
 */
public class CompletableFutureConsumePreviousResult {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "烧水->";
        }, threadPool).thenApply(boiled -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return boiled + "倒茶叶->";
        }).thenApply(drink -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return drink + "盖盖";
        }).thenAccept(result->{
            System.out.println(result);
        });
    }

}
