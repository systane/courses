package com.concurrency.semaphore;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore2 {

    private static final Semaphore SEMAPHORE = new Semaphore(3);
    private static final AtomicInteger QUANTITY = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(101);

        Runnable r1 = () -> {
            String threadName = Thread.currentThread().getName();
            int userId = new Random().nextInt(1000);

            boolean hadAcquire = false;
            QUANTITY.incrementAndGet();
            while (!hadAcquire) {
                hadAcquire = tryAcquire(); //acquire lock based in the quantity of thread allowed to execute per time
            }
            QUANTITY.decrementAndGet();
            System.out.println("user " + userId + " has subscribed to thread " + threadName + "\n");
            sleep();
            SEMAPHORE.release(); //release the lock after finish the process in order to allow another thread execute
        };

        Runnable r2 = () -> System.out.println("threads waiting to be subscribed: " + QUANTITY.get());

        for (int i = 0; i < 100; i++) {
            scheduleThreadPool.submit(r1);
        }

        scheduleThreadPool.scheduleWithFixedDelay(r2, 0 , 1, TimeUnit.SECONDS);
    }

    private static boolean tryAcquire() {
        try {
            //stay waiting for the permission for 1 seconds, if successful then return true, otherwise false
            return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }
    }

    private static void sleep() {
        int timeToWait = new Random().nextInt(6);
        timeToWait++;
        try {
            Thread.sleep(1000 * timeToWait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
