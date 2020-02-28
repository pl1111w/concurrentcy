package com.xiaobo.concurrent.example.atomic;

import com.xiaobo.concurrent.annotations.ThreadSafe;
import com.xiaobo.concurrent.test.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

@ThreadSafe
public class AtomicExample3 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    /**
     * 当请求数量太多时，atomic判断修改的成功性降低，程序性能会受到影响
     * longAdder(hash算法单点变多点，统计时有并非更新可能有误差)性能优于atomic
     **/
    public static LongAdder count = new LongAdder();

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    logger.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("count:{}", count);
    }

    private static void add() {
        count.increment();
    }
}
