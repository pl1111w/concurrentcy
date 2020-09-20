package com.pl1111w.concurrent.example.blockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.PriorityBlockingQueue;

@Slf4j
public class PriorityTaskExample implements Runnable, Comparable {

    private int priority;

    public PriorityTaskExample(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        PriorityTaskExample task = (PriorityTaskExample) o;
        if (this.priority == task.priority) {
            return 0;
        }
        return this.priority > task.priority ? 1 : -1;
    }

    @Override
    public void run() {
        log.info("优先级为" + priority + "的任务执行完毕！");
    }

    //为了做对比，先看一下普通队列的元素存取顺序,再看一个优先级队列
    //LinkedBlockingQueue采用先进先出FIFO的顺序来取元素
    //PriorityBlockingQueue里的元素必须实现Comparable接口，它也是先进先出的，只是它已经根据元素的compareTo方法事先排好
    public static void blockingQueue(final BlockingQueue<PriorityTaskExample> queue) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int priority = random.nextInt(1000);
            System.out.println("元素优先级：" + priority);
            queue.put(new PriorityTaskExample(priority));
        }

        //开启线程消费队列中的任务
        new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        //取出队列中元素
                        queue.take().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        // System.out.println("******开始测试普通阻塞队列******");
        // blockingQueue(new LinkedBlockingQueue<PriorityTask>());
        // TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("******开始测试优先级队列******");
        blockingQueue(new PriorityBlockingQueue<PriorityTaskExample>());
    }
}
