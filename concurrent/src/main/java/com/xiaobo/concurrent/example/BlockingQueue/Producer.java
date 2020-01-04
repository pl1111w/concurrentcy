package com.xiaobo.concurrent.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
@Slf4j
public class Producer implements Runnable {

    private BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        log.info("生产一个元素...");
        queue.add("1");
    }
}
