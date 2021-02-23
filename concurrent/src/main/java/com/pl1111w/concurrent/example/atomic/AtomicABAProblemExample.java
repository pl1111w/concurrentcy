package com.pl1111w.concurrent.example.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @title: pl1111w
 * @description: ABA问题
 * @author: Kris
 * @date 2021/2/23 21:44
 */
public class AtomicABAProblemExample {

    /**
     * 不带时间戳
     **/
    public static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    /**
     * 带时间戳
     **/
    public static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        //****************************ABA问题************************************
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 102);
            System.out.println(atomicReference.get());
        }, "t2").start();

        //****************************解决ABA************************************
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次 stamp: " + stamp);
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第二次 stamp: " + stampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次 stamp: " + stampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = stampedReference.compareAndSet(100, 1024, stamp, stamp + 1);
            System.out.println(result);
            System.out.println(Thread.currentThread().getName() + "--" + stampedReference.getReference());
        }, "t4").start();

    }

}
