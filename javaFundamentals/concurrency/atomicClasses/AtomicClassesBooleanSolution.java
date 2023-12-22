package com.concurrency.atomicClasses;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicClassesBooleanSolution {

    static AtomicBoolean b = new AtomicBoolean(false); //shared resource between threads per jvm (static resource)

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);


        t0.start();
        t1.start();
        t2.start();

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + b.compareAndExchange(false, true)); //thread-safe while compare and change value
        }
    }
}
