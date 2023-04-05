package com.pl1111w.concurrent.example.threadPool;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

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
                /*
                    execute和submit都属于线程池的方法，execute只能提交Runnable类型的任务，
                    而submit既能提交Runnable类型任务也能提交Callable类型任务。
                    execute会直接抛出任务执行时的异常，submit会吃掉异常，
                    可通过Future的get方法将任务执行时的异常重新抛出。
                 */
                // executor.execute(() -> {   //会抛异常
                Future<?> future = executor.submit(() -> {     //不会抛异常 futureTask 处理了
                    System.out.println(10 / (a - b)); //抛异常的线程不会 被重新放回线程池
                    System.out.println(Thread.currentThread().getName() + "\t" + "开始任务");
                });
                Object o = future.get();//java.lang.ArithmeticException: / by zero
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.out.println("shut down ....");
        }

    }

}
