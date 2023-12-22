package com.concurrency.atomicClasses;

public class AtomicClassesIntProblem {

    static int i = -1; //shared resource between threads per jvm (static resource)

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            i++;
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i);
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
