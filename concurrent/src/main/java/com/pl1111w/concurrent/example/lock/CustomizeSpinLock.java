package com.pl1111w.concurrent.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @title: pl1111w
 * @description: 自定义自旋锁
 * @author: Kris
 * @date 2021/2/26 22:55
 */
public class CustomizeSpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        CustomizeSpinLock customizeSpinLock = new CustomizeSpinLock();
        new Thread(()->{
            customizeSpinLock.getLock();
            try {
                TimeUnit.SECONDS.sleep(5);
                customizeSpinLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"Thread001").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            customizeSpinLock.getLock();
            customizeSpinLock.unlock();
        },"Thread002").start();
    }

    public void getLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "come in hhh");
        while (!atomicReference.compareAndSet(null, thread)) {

        }

    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        while (atomicReference.compareAndSet(thread, null)) {
            System.out.println(thread.getName() + "unLock lululu");
        }
    }
}
