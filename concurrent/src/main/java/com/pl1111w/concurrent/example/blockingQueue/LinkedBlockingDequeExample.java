package com.pl1111w.concurrent.example.blockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class LinkedBlockingDequeExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new LinkedBlockingDeque(2);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        Thread.sleep(4000);//防止抛异常
        log.info("队列是否为空：" + queue.isEmpty());
        new Thread(consumer).start();


    }
}
