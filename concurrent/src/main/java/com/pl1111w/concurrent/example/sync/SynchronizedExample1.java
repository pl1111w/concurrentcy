package com.pl1111w.concurrent.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    /**
     * 同步代码块（当一个方法内只有同步代码块时于同步方法等同）
     **/
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                log.info("test1:{}", i);
            }
        }
    }

    /**
     * 同步方法
     **/
    public synchronized void test2() {
        for (int i = 1; i <= 5; i++) {
            log.info("test2:{}", i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        /**同一个对象调用先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test1());
        executorService.execute(() -> example1.test1());
        /**同一个对象调用先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test2());
        executorService.execute(() -> example1.test2());
        /**不同对象调用同步代码块时，互相不影响**/
        executorService.execute(() -> example1.test1());
        executorService.execute(() -> example2.test1());
        /**不同对象调用同步方法时，互相不影响**/
        executorService.execute(() -> example1.test2());
        executorService.execute(() -> example2.test2());
    }
}
