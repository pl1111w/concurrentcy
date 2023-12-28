package com.pl1111w.concurrent.example.threadLocal;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2023/12/7 12:43
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        RequestHolder.add(11L);
        RequestHolder.add(12L);

        new Thread(() -> {
            RequestHolder.add(16L);
            System.out.println(Thread.currentThread().getName() + "" + RequestHolder.getId());
        }).start();

        RequestHolder.add(13L);
        System.out.println(Thread.currentThread().getName() + "\t" + RequestHolder.getId());
    }
}
