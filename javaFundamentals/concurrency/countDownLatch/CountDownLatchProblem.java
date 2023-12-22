package com.concurrency.countDownLatch;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatchProblem {

    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);

        Runnable r1 = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
        };


        Optional<Long> optional = Optional.of(1L);
        optional.ifPresent(value -> System.out.printf(value.toString()));
        scheduledExecutor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);

        //trying to increment i each 3 seconds in order to attempt to increment i each 3 execution, but it is not possible.
        while (true) {
            Thread.sleep(3000);
            i = new Random().nextInt(1000);
        }
    }

}
