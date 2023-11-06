package com.pl1111w.concurrent.example.sington;

import com.pl1111w.concurrent.annotations.ThreadSafe;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2023/7/20 22:46
 */
@ThreadSafe
public class singletonExample6 {

    private singletonExample6() {

    }

    private static class LazyHolder {
        static final singletonExample6 INSTANCE = new singletonExample6();
    }

    //第一次调用getInstance 才会导致内部类加载和初始化其静态成员
    public static singletonExample6 getInstance() {
        return LazyHolder.INSTANCE;
    }
}
