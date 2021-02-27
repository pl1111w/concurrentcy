package com.pl1111w.concurrent.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockExample {
    private Map<String, Integer> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();


    public static void main(String[] args) {

        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 6; i++) {
            final Integer tempInt = i;
            executorService.execute(() -> {
                example.put(tempInt.toString(), tempInt);
            });
        }


        try {
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 6; i++) {
            final Integer tempInt = i;
            executorService.execute(() -> {
                example.get(tempInt.toString());
            });
        }
    }


    public int get(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-开始读：" + map.get(key));
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, Integer value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-开始写：" + key);
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }


}

