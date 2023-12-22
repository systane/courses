package com.concurrency.synchronousQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue1 {

    //only works with a pair of threads that put and read values from queue
    public static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String value = "Subscribe";
            put(value);
            System.out.println("Wrote in the queue the value: " + value);
        };

        Runnable r2 = () -> {
            System.out.println("Read from queue the value: " + take());
        };

        cachedThreadPool.submit(r1);
        cachedThreadPool.submit(r2);
        cachedThreadPool.shutdown();
    }

    private static void put(String message) {
        try {
            QUEUE.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static String take() {
        try {
            return QUEUE.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exception!!!";
        }
    }

}
