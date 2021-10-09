package com.pl1111w.concurrent.example.threadTest;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2021/9/27 21:26
 */
public class ThreadExample {

    public static void main(String[] args) {

        A a = new A();
        a.setName("Thread_0001");

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

        a.start();

        new Thread( ){
            @Override
            public void run(){
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }

        }.start();

    }


}

class A extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}