package com.pl1111w.concurrent.example.atomic;

import com.pl1111w.concurrent.annotations.ThreadSafe;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
public class AtomicIntegerFieldUpdaterExample {

    @Getter
    public volatile int count = 100;

    private static final Logger logger = LoggerFactory.getLogger(AtomicIntegerFieldUpdaterExample.class);


    public static void main(String[] args) {
        /**更新某个类的字段值**/
        AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample> updater =
                AtomicIntegerFieldUpdater.newUpdater(
                        AtomicIntegerFieldUpdaterExample.class, "count");

        AtomicIntegerFieldUpdaterExample atomicExample5 = new AtomicIntegerFieldUpdaterExample();
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            logger.info("compareAndSet succeed!" + atomicExample5.count);
        }
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            logger.info("compareAndSet succeed!" + atomicExample5.count);
        } else {
            logger.info("compareAndSet failed!" + atomicExample5.count);
        }

    }


}
