package com.xiaobo.concurrent.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Consumer_Array implements Runnable {

    private BlockingQueue<Integer> queue;

    public Consumer_Array(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                log.info("取出来的元素是：" + queue.take());
            }
        } catch (Exception e) {
            log.info("消费者在等待新产品的时候被打断了！");
            e.printStackTrace();
        }

    }
}