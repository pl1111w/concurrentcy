package com.pl1111w.concurrent.example.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2021/10/17 21:59
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountNumber countNumber = new CountNumber();
        FutureTask task = new FutureTask(countNumber);
        new Thread(task).start();
        System.out.println(task.get());
    }
}

class CountNumber implements Callable {

    @Override
    public Object call() throws Exception {
        int number = 0;
        for (int i = 0; i < 10; i++) {
            number += i;
        }
        return number;
    }
}
