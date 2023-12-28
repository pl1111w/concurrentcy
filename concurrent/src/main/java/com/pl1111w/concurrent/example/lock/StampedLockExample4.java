package com.pl1111w.concurrent.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @title: pl1111w
 * @description: 乐观读
 * @author: Kris
 * @date 2023/12/28 15:00
 */
public class StampedLockExample4 {

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

    public void optimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println(Thread.currentThread().getName() + " " + i + " reading ... " +
                        " whether lock is change:" + stampedLock.validate(stamp));
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("lock need promotion pessimistic-read lock");
            stamp = stampedLock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + " get number: " + number);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=======optimistic read ======");
        StampedLockExample4 example = new StampedLockExample4();
        new Thread(example::optimisticRead, "Thread-Read").start();
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(example::write, "Thread-Write").start();
    }
}
