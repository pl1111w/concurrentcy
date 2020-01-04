package com.xiaobo.concurrent.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
@Slf4j
public class Consumer implements Runnable {
    private BlockingQueue blockingQueue = null;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        log.info("消费一个元素:"+blockingQueue.remove());
    }
}
