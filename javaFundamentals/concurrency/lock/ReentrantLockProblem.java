package com.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLockProblem {

    private static int i = -1;

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String threadName = Thread.currentThread().getName();
            i++;
            System.out.println(threadName + " reading and incrementing " + i);
        };

        for (int i = 0; i < 6; i++) {
            cachedThreadPool.submit(r1);
        }

        cachedThreadPool.shutdown();
    }

}
