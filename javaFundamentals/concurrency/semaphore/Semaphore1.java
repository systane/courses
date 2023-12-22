package com.concurrency.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore1 {

    private static final Semaphore SEMAPHORE = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String threadName = Thread.currentThread().getName();
            int userId = new Random().nextInt(1000);

            acquire(); //acquire lock based in the quantity of thread allowed to execute per time
            System.out.println("user " + userId + " has subscribed to thread " + threadName + "\n");
            sleep();
            SEMAPHORE.release(); //release the lock after finish the process in order to allow another thread execute
        };

        for (int i = 0; i < 100; i++) {
            cachedThreadPool.submit(r1);
        }

        cachedThreadPool.shutdown();
    }

    private static void acquire() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
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
