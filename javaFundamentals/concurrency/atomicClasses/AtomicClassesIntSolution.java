package com.concurrency.atomicClasses;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClassesIntSolution {

    static AtomicInteger i = new AtomicInteger(-1); //shared resource between threads per jvm (static resource)

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i.incrementAndGet()); //thread-safe while increment variable
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);


        t0.start();
        t1.start();
        t2.start();


    }

}
