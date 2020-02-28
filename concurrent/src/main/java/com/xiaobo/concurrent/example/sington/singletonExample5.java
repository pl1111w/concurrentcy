package com.xiaobo.concurrent.example.sington;

import com.xiaobo.concurrent.annotations.NotRecommend;
import com.xiaobo.concurrent.annotations.ThreadSafe;

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
