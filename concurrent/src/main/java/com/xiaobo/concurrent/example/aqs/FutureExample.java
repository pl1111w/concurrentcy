package com.xiaobo.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {


        @Override
        public String call() throws Exception {
            log.info("run MyCallable method");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        try {
            log.info("run main method..");
            Thread.sleep(1000);
            String result = future.get();
            log.info("result:{}",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
