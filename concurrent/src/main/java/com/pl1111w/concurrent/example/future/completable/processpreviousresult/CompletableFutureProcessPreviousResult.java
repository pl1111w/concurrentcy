package com.pl1111w.concurrent.example.future.completable.processpreviousresult;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: 对计算结果进行处理
 * @author: Kris
 * @date 2023/11/5 22:54
 */
public class CompletableFutureProcessPreviousResult {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
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
        });
        System.out.println(completableFuture.join());
        System.out.println("========下面case 有异常不继续执行===========");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("烧水完成！");
            return "烧水->";
        }, threadPool).thenApply(boiled -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(10/0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("选茶叶完成！");
            return boiled + "倒茶叶->";
        }).thenApply(drink -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return drink + "盖盖";
        });
        System.out.println(completableFuture2.join());

        System.out.println("*****下面case 有异常继续执行******");
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("烧水完成！");
            return "烧水->";
        }, threadPool).handle((boiled,e) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(10/0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("选茶叶完成！");
            return boiled + "倒茶叶->";
        }).handle((drink,e) -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            return drink + "盖盖";
        }).whenComplete((v,e)->{
            if(e==null){
                System.out.println(v);
            }
        }).exceptionally(e->{
            System.out.println(e);
            return null;
        });
    }

}
