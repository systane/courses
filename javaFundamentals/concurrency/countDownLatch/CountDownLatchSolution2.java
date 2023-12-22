package com.concurrency.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSolution2 {

    private static volatile int i = 0;
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);

        Runnable r1 = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            latch.countDown();
        };
        Runnable r2 = () -> {
            await();
            i = new Random().nextInt(1000);
        };
        Runnable r3 = () -> {
            await();
            latch = new CountDownLatch(3);
        };
        Runnable r4 = () -> {
            System.out.println("process finished. Let's start again");
        };

        scheduledExecutor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);
        scheduledExecutor.scheduleWithFixedDelay(r2, 0, 1, TimeUnit.SECONDS);
        scheduledExecutor.scheduleWithFixedDelay(r3, 0, 1, TimeUnit.SECONDS);
        scheduledExecutor.scheduleWithFixedDelay(r4, 0, 1, TimeUnit.SECONDS);
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
