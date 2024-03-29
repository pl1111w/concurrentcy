package com.pl1111w.concurrent.example.atomic;

import com.pl1111w.concurrent.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);//yes
        count.compareAndSet(0, 1);//no
        count.compareAndSet(1, 3);//no
        count.compareAndSet(2, 4);//yes
        count.compareAndSet(3, 5);//no

        System.out.println(count.get());//4
    }
}
