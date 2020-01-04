package com.xiaobo.concurrent.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ArrayBlockingQueueExample {
    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3,true);
       /* Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        producer.run();
        blockingQueue.element();
        log.info("元素值为："+blockingQueue.element());
        consumer.run();*/

        Producer_Array producerArray = new Producer_Array(blockingQueue);
        Thread producer_Array = new Thread(producerArray);
        producer_Array.start();
        Consumer_Array consumerArray = new Consumer_Array(blockingQueue);
        Thread Consumer_Array =  new Thread(consumerArray);
        Consumer_Array.start();
        try {
            Consumer_Array.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
