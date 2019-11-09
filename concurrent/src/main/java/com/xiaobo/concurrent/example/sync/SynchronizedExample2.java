package com.xiaobo.concurrent.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    /**
     * 修饰一个类
     **/
    public void test1(String flag) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 5; i++) {
                log.info("test1:{}{}",flag, i);
            }
        }
    }

    /**
     * 修饰静态方法
     **/
    public static synchronized void test2(String flag) {
        for (int i = 1; i <= 5; i++) {
            log.info("test2:{}{}", flag,i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        /**同一个对象调用同步类先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test1("one"));
        executorService.execute(() -> example1.test1("two"));
        /**同一个对象调用【静态】同步方法先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test2("one"));
        executorService.execute(() -> example1.test2("two"));
       /**不同对象调用同步类时，先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test1("one"));
        executorService.execute(() -> example2.test1("two"));
       /**不同对象调用【静态】同步方法时，先执行完第一个在执行第二个**/
        executorService.execute(() -> example1.test2("one"));
        executorService.execute(() -> example2.test2("two"));
    }
}
