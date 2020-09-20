package com.pl1111w.concurrent.example.sington;

import com.pl1111w.concurrent.annotations.NotThreadSafe;

@NotThreadSafe
//懒汉模式 线程不安全
public class singletonExample1 {

    private static singletonExample1 instance = null;

    private singletonExample1() {
        //逻辑代码
    }

    public static singletonExample1 getInstance() {
        if (instance == null) {
            return new singletonExample1();
        }
        return instance;
    }
}
