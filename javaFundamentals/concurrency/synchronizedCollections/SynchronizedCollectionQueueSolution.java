package com.concurrency.synchronizedCollections;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SynchronizedCollectionQueueSolution {

    //shared resource between threads per jvm (static resource)
    //thread-safe queue - wrapper around add method, in other words, it adds a synchronized keyword in add method from queue class
    static Queue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);


        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);

        //there is no guarantee that our list is going to have 3 elements even we are using thread.sleep
        System.out.println(queue);

    }


    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            queue.add("synchronized collection!");
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " inserted a new record in the queue");
        }
    }
}
