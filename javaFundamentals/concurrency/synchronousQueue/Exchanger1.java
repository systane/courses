package com.concurrency.synchronousQueue;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exchanger1 {

    //only works with a pair of threads that needs to exchange message (both ways) to each other
    public static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String threadName = Thread.currentThread().getName();
            String messageFromThread2 = exchange("message from thread 1");
            System.out.println(threadName + " received " + messageFromThread2);
        };

        Runnable r2 = () -> {
            String threadName = Thread.currentThread().getName();
            String messageFromThread1 = exchange("message from thread 2");
            System.out.println(threadName + " received " + messageFromThread1);
        };

        cachedThreadPool.submit(r1);
        cachedThreadPool.submit(r2);
        cachedThreadPool.shutdown();
    }

    private static String exchange(String messageToSend) {
        try {
            return EXCHANGER.exchange(messageToSend);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exception!!!";
        }
    }

}
