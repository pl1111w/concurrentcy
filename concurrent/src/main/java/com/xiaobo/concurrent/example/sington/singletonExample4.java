package com.xiaobo.concurrent.example.sington;

import com.xiaobo.concurrent.annotations.Recommend;
import com.xiaobo.concurrent.annotations.ThreadSafe;

@ThreadSafe
@Recommend
public class singletonExample4 {

    //1、memory = allocate()分配对象内存空间
    //2、ctorInstance()初始化对象
    //3、instance = ctorInstance()//设置instance指向刚分配的内存

    //1、memory = allocate()分配对象内存空间
    //3、ctorInstance()初始化对象
    //2、instance = ctorInstance()//设置instance指向刚分配的内存
    private singletonExample4() {

    }

    //禁止指令重排
    private volatile static singletonExample4 instance = null;

    public static singletonExample4 getInstance() {
        if (instance == null) {
            synchronized (singletonExample4.class) {
                if (instance == null) {
                    return instance = new singletonExample4();
                }
            }
        }
        return instance;
    }
}
