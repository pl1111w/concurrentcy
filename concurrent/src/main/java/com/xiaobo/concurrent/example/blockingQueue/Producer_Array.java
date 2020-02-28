package com.xiaobo.concurrent.example.blockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Producer_Array implements Runnable {

    private BlockingQueue<Integer> blockingQueue;
    private static int element = 0;

    public Producer_Array(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public void run() {
        try {
            while (element < 20) {
                blockingQueue.put(element);
                log.info("已经放进去的元素是：" + element);
                element++;
            }
        } catch (Exception e) {
            log.error("生产者在等待空闲空间的时候被打断了！");
            log.error("Exception:", e);
        }
        log.info("生产者已经终止了生产过程！");
    }

}