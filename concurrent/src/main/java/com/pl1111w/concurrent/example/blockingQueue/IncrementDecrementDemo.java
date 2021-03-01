package com.pl1111w.concurrent.example.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @title: pl1111w
 * @description: 两个线程交替加减1
 * @author: Kris
 * @date 2021/3/1 22:10
 */
public class IncrementDecrementDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int j = 0; j < 5; j++) {
                shareData.incrementNumber();
            }
        }, "AAA").start();
        new Thread(() -> {
            for (int j = 0; j < 5; j++) {
                shareData.decrementNumber();
            }
        }, "BBB").start();

    }


}

class ShareData {
    int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void incrementNumber() {
        try {
            lock.lock();
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signal(); // 等价 this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrementNumber() {
        try {
            lock.lock();
            while (number != 1) { //切记别写为if判断！！！！
                condition.await(); // 等价 this.wait();

            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}