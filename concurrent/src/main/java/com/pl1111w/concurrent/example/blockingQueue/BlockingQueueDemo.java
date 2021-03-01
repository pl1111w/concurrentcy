package com.pl1111w.concurrent.example.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @title: pl1111w
 * @description: 阻塞队列
 * @author: Kris
 * @date 2021/3/1 20:38
 */
public class BlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);

//        System.out.println(blockingQueue.add(1));
//        System.out.println(blockingQueue.add(2));
//        System.out.println(blockingQueue.add(3));
//        System.out.println(blockingQueue.add(4));//java.lang.IllegalStateException: Queue full
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());//java.util.NoSuchElementException

//        System.out.println(blockingQueue.offer(1));
//        System.out.println(blockingQueue.offer(2));
//        System.out.println(blockingQueue.offer(3));
//        System.out.println(blockingQueue.offer(4));//false
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());//null



        System.out.println(blockingQueue.offer(1,2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(2,2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(3,2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(4,2,TimeUnit.SECONDS));//两秒后 false

        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));//两秒后 null

//            blockingQueue.put(1);
//            blockingQueue.put(2);
//            blockingQueue.put(3);
//            blockingQueue.put(4);//程序一直跑 队列已经满了 死等！！！
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());////程序一直跑 队列已经空了取不出来 死等！！！
    }
}
