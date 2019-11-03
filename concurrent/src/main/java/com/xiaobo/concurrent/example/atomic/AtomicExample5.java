package com.xiaobo.concurrent.example.atomic;

import com.xiaobo.concurrent.annotations.ThreadSafe;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
public class AtomicExample5 {

    @Getter
    public volatile int count = 100;

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample5.class);


    public static void main(String[] args) {
        /**更新某个类的字段值**/
        AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

        AtomicExample5 atomicExample5 = new AtomicExample5();
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            logger.info("compareAndSet succeed!"+atomicExample5.count);
        }
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            logger.info("compareAndSet succeed!"+atomicExample5.count);
        } else {
            logger.info("compareAndSet failed!"+atomicExample5.count);
        }

    }


}
