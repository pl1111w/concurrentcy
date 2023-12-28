package com.pl1111w.concurrent.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @title: pl1111w
 * @description:  悲观读不允许写锁介入
 * @author: Kris
 * @date 2023/12/28 15:00
 */
public class StampedLockExample3 {

    static int number = 2023;

    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println("write ready ...");
        try {
            number++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println("write finish **");
    }

    public void read() {
        long stamp = stampedLock.readLock();
        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println(Thread.currentThread().getName() + "reading ... ");
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        try {
            System.out.println(Thread.currentThread().getName() + " get number: " + number);
        } finally {
            stampedLock.unlockRead(stamp);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======pessimistic read ======");
        StampedLockExample3 example = new StampedLockExample3();
        new Thread(example::read, "Thread-Read").start();
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(example::write, "Thread-Write").start();
    }
}
