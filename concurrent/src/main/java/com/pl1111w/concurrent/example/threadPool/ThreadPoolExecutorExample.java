package com.pl1111w.concurrent.example.threadPool;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: 自定义线程池
 * @author: Kris
 * @date 2021/3/7 13:28
 */
public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new LinkedBlockingDeque(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 8; i++) {
                final int a = 5;
                final int b = i;
               // executor.execute(() -> {   //会抛异常
                executor.submit(() -> {     //不会抛异常 futureTask 处理了
                    System.out.println(10 / (a - b)); //抛异常的线程不会 被重新放回线程池
                    System.out.println(Thread.currentThread().getName() + "\t" + "开始任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
