package com.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorHelloWorld {

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("thread " + threadName + " is executing...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new MyRunnable());
        singleThreadExecutor.execute(new MyRunnable());
        singleThreadExecutor.awaitTermination(5, TimeUnit.SECONDS); //wait for 5 seconds
        singleThreadExecutor.shutdown();
    }

}
