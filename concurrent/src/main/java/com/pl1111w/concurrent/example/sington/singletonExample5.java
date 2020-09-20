package com.pl1111w.concurrent.example.sington;

import com.pl1111w.concurrent.annotations.NotRecommend;
import com.pl1111w.concurrent.annotations.ThreadSafe;

@ThreadSafe
@NotRecommend
//饿汉加载
/**
 * 构造函数逻辑多，影响性能
 * 类未被使用，浪费空间
 * **/
public class singletonExample5 {

    private singletonExample5() {
        //逻辑
    }

    private static singletonExample5 instance = new singletonExample5();

    public static singletonExample5 getInstance() {
        return instance;
    }
}
