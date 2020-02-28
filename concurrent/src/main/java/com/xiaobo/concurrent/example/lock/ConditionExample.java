package com.xiaobo.concurrent.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionExample {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {//线程1
            try {
                reentrantLock.lock();//必须获取锁才能执行await方法
                log.info("wait signal"); // 1
                condition.await();//进入等待队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {//线程2
            reentrantLock.lock();//拿到锁
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();//线程1从等待队列重新进入同步队列，此时锁被线程2持有
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
        }).start();
    }
}
