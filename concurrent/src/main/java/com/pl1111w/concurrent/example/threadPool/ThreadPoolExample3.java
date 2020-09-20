package com.pl1111w.concurrent.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    log.info("schedule...");
                }
            }, 3, TimeUnit.SECONDS);
        }
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("延迟1s 每3s执行一次..");
            }
        }, 1, 3, TimeUnit.SECONDS);


        for (int j = 0; j < 10; j++) {
            executorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    log.info("scheduleWithFixedDelay");
                }
            }, 2, 3, TimeUnit.SECONDS);
        }
    }
}
