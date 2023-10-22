package com.pl1111w.concurrent.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("run MyCallable method");
            Thread.sleep(3000);
            return "Done";
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        try {
            log.info("run main method..");
            Thread.sleep(4000);
            String result = future.get();
            long end = System.currentTimeMillis();
            log.info("get result:{} consume time: {} ", result, end - start);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
