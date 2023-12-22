package com.concurrency.executors;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorScheduledThread3 {

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalTime.now());
            String threadName = Thread.currentThread().getName();
            System.out.println("thread " + threadName+ " with value " + new Random().nextInt(1000));
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledThreadPool = null;

        scheduledThreadPool = Executors.newScheduledThreadPool(3);

        //fixed delay between each task execution.
        scheduledThreadPool.scheduleWithFixedDelay(new Task(), 0, 1, TimeUnit.SECONDS);

    }
}
