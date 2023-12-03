package com.pl1111w.concurrent.example.atomic;

import com.pl1111w.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicMarkableReference;

@Slf4j
@ThreadSafe
public class AtomicMarkableReferenceExample {
    /**
     * 多线程只能被修改一次 一旦有线程执行了后面线程无法修改
     * 类似一次性筷子 第二次匹配mark value 是正确的也无法修改
     **/
    private static final AtomicMarkableReference<Integer> isHappened = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(() -> {
            boolean marked = isHappened.isMarked();
            log.info("default mark: " + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            isHappened.compareAndSet(100, 1000, false, true);
            countDownLatch.countDown();
            log.info("finished mark: " + isHappened.isMarked());

        });
        executorService.execute(() -> {
            boolean marked = isHappened.isMarked();
            log.info("default mark: " + marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            isHappened.compareAndSet(1000, 2000, false, false);
            countDownLatch.countDown();
            log.info("finished mark: " + isHappened.isMarked());

        });
        executorService.shutdown();
        countDownLatch.await();
        log.info("result: {}", isHappened.getReference());

    }


}