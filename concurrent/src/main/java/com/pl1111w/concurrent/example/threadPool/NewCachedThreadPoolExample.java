package com.pl1111w.concurrent.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class NewCachedThreadPoolExample {
    /**
     * maximumPoolSize： 最大线程数为Integer.MAX_VALUE 线程创建过多导致oom
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("index:" + index);
                }
            });
        }
        executorService.shutdown();

    }
}
