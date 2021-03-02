package com.pl1111w.concurrent.example.blockingQueue;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title: pl1111w
 * @description: 生产者消费者
 * @author: Kris
 * @date 2021/3/2 23:27
 */
public class IncrementDecrementPlusDemo {

    private volatile boolean FLAG = true;

    private BlockingQueue<String> blockingQueue = null;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        IncrementDecrementPlusDemo incrementDecrementPlusDemo = new IncrementDecrementPlusDemo(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                incrementDecrementPlusDemo.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Prod").start();

        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                incrementDecrementPlusDemo.myConsu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Consu").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        incrementDecrementPlusDemo.myStop();
    }

    public IncrementDecrementPlusDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retvalue;

        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retvalue = blockingQueue.offer(data,3,TimeUnit.SECONDS);
            if (retvalue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列 " + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列 " + data + " 失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("生产停止。。。。。" + "FLAG=" + FLAG);
    }

    public void myConsu() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(3,TimeUnit.SECONDS);
            if (StringUtils.isEmpty(result)) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有队列，消息退出！");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列 " + result + " 成功");
        }
    }

    public void myStop() {
        this.FLAG = false;
    }
}