package com.pl1111w.concurrent.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @title: pl1111w
 * @description: 锁绑定多个条件
 * @author: Kris
 * @date 2021/3/1 22:29
 */
public class ConditionsOfLock {

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    char mark = 'A';

    public static void main(String[] args) {

        ConditionsOfLock conditionsOfLock = new ConditionsOfLock();
        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                conditionsOfLock.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                conditionsOfLock.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                conditionsOfLock.print15();
            }
        }, "CC").start();
    }

    public void print5() {
        lock.lock();
        try {
            while (mark != 'A') {
                c1.await();
            }
            for (int i = 1; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            mark = 'B';
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (mark != 'B') {
                c2.await();
            }
            for (int i = 1; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            mark = 'C';
            c3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (mark != 'C') {
                c3.await();
            }
            for (int i = 1; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            mark = 'A';
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
