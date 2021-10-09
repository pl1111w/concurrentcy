package com.pl1111w.concurrent.example.threadTest;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2021/10/9 12:44
 */
public class ThreadMethodTest extends Thread {

    public static void main(String[] args) {
        ThreadMethodTest threadMethodTest = new ThreadMethodTest();

        new Thread("thread_01") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 5 == 0) {
                        yield();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }.start();
        new Thread("thread_02") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }.start();
        try {
            threadMethodTest.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread_A thread_a = new Thread_A();
        thread_a.setName("Thread_A");
        thread_a.start();
        for (int i = 0; i < 100; i++) {
            try {
                if (i == 50) thread_a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        System.out.println("isAlive:" + thread_a.isAlive());
    }

}

class Thread_A extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
