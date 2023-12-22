package com.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorSingleThread2 {

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("thread " + threadName + " is executing...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService singleThreadExecutor = null;
        try {
            singleThreadExecutor = Executors.newSingleThreadExecutor();

            Future<?> future = singleThreadExecutor.submit(new MyRunnable());
            singleThreadExecutor.submit(new MyRunnable());
            singleThreadExecutor.execute(new MyRunnable());
            singleThreadExecutor.execute(new MyRunnable());

            System.out.println(future.isDone());
            singleThreadExecutor.shutdown();
            singleThreadExecutor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (singleThreadExecutor != null) singleThreadExecutor.shutdownNow();
        }
    }

}
