package com.pl1111w.concurrent.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        /**
         *
         * Executors.newFixedThreadPool 默认队列长度：Integer.MAX_VALUE
         *     如果任务执行时间过长导致，队列越积越多 可能出现oom
         *      public LinkedBlockingQueue() {
         *         this(Integer.MAX_VALUE);
         *     }
         */
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> log.info(Thread.currentThread().getName() + "===" + index));
        }
        executorService.shutdown();
    }
}
