package com.concurrency.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSolution1 {

    private static volatile int i = 0;
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);

        Runnable r1 = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            latch.countDown();
        };

        scheduledExecutor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);

        while (true) {
            latch.await();
            i = new Random().nextInt(1000);

            //countDownLatch is not cyclic, every time the latch reach 0, you need to manually restart the counter again
            latch = new CountDownLatch(3);
        }
    }

}
