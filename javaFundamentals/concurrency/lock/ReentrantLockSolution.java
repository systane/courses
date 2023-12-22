package com.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSolution {

    private static int i = -1;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            lock.lock(); //lock one thread until its released/unlocked.
            String threadName = Thread.currentThread().getName();
            i++;
            System.out.println(threadName + " reading and incrementing " + i);
            lock.unlock();
        };

        for (int i = 0; i < 6; i++) {
            cachedThreadPool.submit(r1);
        }

        cachedThreadPool.shutdown();
    }

}
