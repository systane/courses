package com.concurrency.helloWorldSynchronize;

public class SynchronizedSolution3 {

    static int i = -1; //shared resource between threads

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);


        //there is no guarantee that the runnable code are going to be executed in sequence
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    //synchronized in block: We allow only 1 thread to execute this block of code per time inside my jvm.
    // Each thread needs to wait another thread to finish the execution
    public static void print() {
        synchronized (SynchronizedSolution3.class) {
            i++;
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i);
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            print();
        }
    }
}
