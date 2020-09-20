package com.pl1111w.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    log.error("InterruptedException:", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info(threadNum + " is ready");
        try {
            cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);//等1秒在判断是否累计到屏障数
            //累计到屏障数继续执行
            log.info(threadNum + " begin race ...");
        } catch (BrokenBarrierException | TimeoutException e) {
            log.error("BarrierException", e);
        }


    }

}
