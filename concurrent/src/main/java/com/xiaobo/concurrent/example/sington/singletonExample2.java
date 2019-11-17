package com.xiaobo.concurrent.example.sington;

import com.xiaobo.concurrent.annotations.NotRecommend;
import com.xiaobo.concurrent.annotations.ThreadSafe;

@NotRecommend
@ThreadSafe
//懒汉模式 但synchronized 导致性能低
public class singletonExample2 {

    private singletonExample2() {

    }

    private static singletonExample2 instance = null;

    public static synchronized singletonExample2 getInstance() {
        if (instance == null) {
            return new singletonExample2();
        }
        return instance;
    }
}
