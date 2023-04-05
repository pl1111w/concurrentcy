package com.pl1111w.concurrent.example.threadPool;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2023/4/5 13:33
 */
public class CustomThreadPoolExecutorExample {

    public static void main(String[] args) {
        ThreadPoolExecutor customThreadName = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque(3),
                new CustomizableThreadFactory("Thread-Kris-"),
                new ThreadPoolExecutor.AbortPolicy());
        customThreadName.execute(() -> {
            System.out.println("get thread name...");
            throw new NullPointerException("haha");
        });
    }
}
