package com.pl1111w.concurrent.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(3000);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(4000);
        String result = futureTask.get();
        long end = System.currentTimeMillis();
        log.info("get result:{} consume time: {} ", result, end - start);
    }
}
