package com.pl1111w.concurrent.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @title: pl1111w
 * @description: 可重入锁--synchronized,ReentrantLock
 *                可重入锁指:可重复可递归调用的锁，
 *                在外层使用锁之后，在内层仍然可以使用，
 *                并且不发生死锁（前提得是同一个对象或者class），
 *                这样的锁就叫做可重入锁。
 * @author: Kris
 * @date 2021/2/25 23:15
 */
public class ReentrantLocksExample {

    ReentrantLock reentrantLock = new ReentrantLock();//默认 Nonfair

    public static void main(String[] args) {
        ReentrantLocksExample example = new ReentrantLocksExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> example.getSyn());
        }
        ReentrantLocksExample example2 = new ReentrantLocksExample();

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> example2.getReentrant());
        }

    }

    public synchronized void getSyn() {
        System.out.println(Thread.currentThread().getId() + " method getSyn");
        setSyn();
    }

    public synchronized void setSyn() {
        System.out.println(Thread.currentThread().getId() + " method setSyn");
    }

    public void getReentrant() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getId() + " method getReentrant");
            setReentrant();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void setReentrant() {
        try {
            //lock与unlock 必须成对出现 lock>unlock 缺少解锁无法退出，unlock>lock :IllegalMonitorStateException
            reentrantLock.lock();
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getId() + " method setReentrant");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            reentrantLock.unlock();
        }

    }
}
