package com.xiaobo.concurrent.example.sington;

public class singletonExample3 {

    //1.1、memory = allocate()分配对象内存空间
    //1.2、ctorInstance()初始化对象
    //1.3、instance = ctorInstance()//设置instance指向刚分配的内存

    /**指令重排顺序如下：**/
    //【1】、memory = allocate()分配对象内存空间
    //【3】、ctorInstance()初始化对象
    //【2】、instance = ctorInstance()//设置instance指向刚分配的内存
    private singletonExample3() {

    }

    private static singletonExample3 instance = null;

    public static singletonExample3 getInstance() {
        if (instance == null) {   //线程B 此时线程A进行到3，B判断不为空直接return,对象初始化的数据没有return出去
            synchronized (singletonExample3.class) {
                if (instance == null) {
                    return instance = new singletonExample3();  //线程A
                }
            }
        }
        return instance;
    }
}
