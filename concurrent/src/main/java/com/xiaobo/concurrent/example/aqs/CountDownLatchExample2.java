package com.xiaobo.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    //假设test计算很复杂，分成200个线程进行计算
                   test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //时间超过100ms，countDownLatch不等计数器到0，无论线程是否执行完毕，都会继续执行线程以外的代码
        countDownLatch.await(100,TimeUnit.MILLISECONDS);
        log.info("线程外的代码继续执行....");
        executorService.shutdown();

    }

    public static void test (int i) throws InterruptedException {
       Thread.sleep(100);
       log.info("i="+i);//此行代表复杂的计算
    }
}
