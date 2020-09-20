package com.pl1111w.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample4 {

    private final static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    /**同一个线程一下子获取三个许可,所以每次只有一个线程可以进入，
                     * 线程执行完释放这三个许可，下一个线程再次尝试获取三个许可，
                     * 累计5秒，test方法每次睡眠一秒，故只有5个线程执行完**/
                    if (semaphore.tryAcquire(3, 5000, TimeUnit.MILLISECONDS)) { //尝试获取许可，一个有三个获取到，由于test睡眠一秒，其他线程尝试获取失败
                        test(threadNum);
                        semaphore.release(3);
                    }

                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
