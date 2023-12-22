package com.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockSolution {

    private static int i = -1;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            Lock writeLock = lock.writeLock();//blocks writing and reading actions, only one thread can write per time until unlock
            writeLock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Writing: " + i);
            i++;
            System.out.println(threadName + " Wrote: " + i);
            writeLock.unlock();
        };

        Runnable r2 = () -> {
            Lock readLock = lock.readLock();
            readLock.lock(); //blocks writing action, but many threads can read at the same time
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Reading: " + i);
            System.out.println(threadName + " Read: " + i);
            readLock.unlock();
        };

        for (int i = 0; i < 6; i++) {
            cachedThreadPool.submit(r1);
            cachedThreadPool.submit(r2);
        }

        cachedThreadPool.shutdown();
    }

}
