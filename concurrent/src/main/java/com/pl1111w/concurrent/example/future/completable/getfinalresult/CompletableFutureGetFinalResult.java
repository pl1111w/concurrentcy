package com.pl1111w.concurrent.example.future.completable.getfinalresult;

import java.util.concurrent.*;

/**
 * @title: pl1111w
 * @description: get final result
 * @author: Kris
 * @date 2023/11/2 23:08
 */
public class CompletableFutureGetFinalResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //默认ForkJoinPool 线程池 它守护线程 所以要main sleep >执行时间
        CompletableFuture<Integer> noFeedbackFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = ThreadLocalRandom.current().nextInt(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println(result);
            }

        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });
//        System.out.println("join: "+noFeedbackFuture.join());
//        System.out.println("get: "+noFeedbackFuture.get());
//        System.out.println("get time out: "+noFeedbackFuture.get(1, TimeUnit.SECONDS));//过时不候 抛出timeout异常
//        System.out.println(noFeedbackFuture.getNow(999));//获取结果时 没计算法完毕直接返回999
        if (noFeedbackFuture.complete(11)) {//判断是否计算完毕
            TimeUnit.SECONDS.sleep(1);
            System.out.println("没有等到最终结果："+ noFeedbackFuture.get());
        } else {
            System.out.println(noFeedbackFuture.get());
        }


    }
}
