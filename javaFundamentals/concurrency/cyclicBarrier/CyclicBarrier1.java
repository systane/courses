package com.concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier1 {

    // (432*3) + (3^14) + (45*127/12) = ?
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> {
            System.out.println(432d * 3d);
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        Runnable r2 = () -> {
            System.out.println(Math.pow(3, 14));
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        Runnable r3 = () -> {
            System.out.println(45d * 127d / 12d);
            await(cyclicBarrier);
            System.out.println("process finished");
        };

        fixedThreadPool.submit(r1);
        fixedThreadPool.submit(r2);
        fixedThreadPool.submit(r3);

        fixedThreadPool.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
