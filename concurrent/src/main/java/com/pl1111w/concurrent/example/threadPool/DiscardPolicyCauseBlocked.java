package com.pl1111w.concurrent.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: DiscardPolicy导致线程堵塞
 * <p>
 * future get set timeout filed
 * change other policy
 * @author: Kris
 * @date 2023/4/5 13:03
 */
@Slf4j
public class DiscardPolicyCauseBlocked {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1L,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());

        Future<String> future1 = executor.submit(() -> {
            log.info("future task 1 .......");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1";
        });
        Future<String> future2 = executor.submit(() -> {
            log.info("future task 2 .......");
            return "task1";
        });
        Future<String> future3 = executor.submit(() -> {
            log.info("future task 3 .......");
            return "task3";
        });

        log.info("get task 1 " + future1.get());
        log.info("get task 2 " + future2.get());
        log.info("========never get task 3==========");
        /***
         *   public FutureTask(Runnable runnable, V result) {
         *         this.callable = Executors.callable(runnable, result);
         *         this.state = NEW;       // ensure visibility of callable
         *     }
         *
         *        private volatile int state;
         *         private static final int NEW          = 0;
         *         private static final int COMPLETING   = 1;
         *         private static final int NORMAL       = 2;
         *         private static final int EXCEPTIONAL  = 3;
         *         private static final int CANCELLED    = 4;
         *         private static final int INTERRUPTING = 5;
         *         private static final int INTERRUPTED  = 6;
         *
         *  public V get() throws InterruptedException, ExecutionException {
         *         int s = state;
         *         if (s <= COMPLETING)
         *             s = awaitDone(false, 0L);
         *         return report(s);
         *     }
         */
        log.info("get task 3 " + future3.get(10, TimeUnit.SECONDS));
        log.info("get task 3 " + future3.get());
    }
}
