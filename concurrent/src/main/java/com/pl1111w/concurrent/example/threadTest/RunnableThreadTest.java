package com.pl1111w.concurrent.example.threadTest;

/**
 * @title: pl1111w
 * @description: Runnable 接口创建多线程
 * @author: Kris
 * @date 2021/10/11 15:44
 */
public class RunnableThreadTest {

    public static void main(String[] args) {
        RunnableTest test = new RunnableTest();
        Thread thread1 = new Thread(test,"窗口1");
        Thread thread2 = new Thread(test,"窗口2");
        Thread thread3 = new Thread(test,"窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class RunnableTest implements Runnable {

    int number = 100;

    @Override
    public void run() {
        while (number > 0) {
            System.out.println(Thread.currentThread().getName() + " " + number);
            number--;
        }
    }
}