package com.concurrency.cyclicBarrier;

import java.util.concurrent.*;

public class CyclicBarrier3 {

    private static BlockingQueue<Double> result = new LinkedBlockingQueue<>();
    private static ExecutorService fixedThreadPool;
    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;


    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("thread " + threadName + " is executing...");
        }
    }

    // (432*3) + (3^14) + (45*127/12) = ?
    public static void main(String[] args) throws InterruptedException {
        Runnable sumResultExpression = () -> {
            double finalResult = result.stream().reduce(0d, Double::sum);
            System.out.println("sum of the expression: " + finalResult);
            System.out.println("===========================================");
            restart();

        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, sumResultExpression);

        fixedThreadPool = Executors.newFixedThreadPool(3);

        r1 = () -> {
            result.add(432d * 3d);
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        r2 = () -> {
            result.add(Math.pow(3, 14));
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        r3 = () -> {
            result.add(45d * 127d / 12d);
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        restart();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static void restart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fixedThreadPool.submit(r1);
        fixedThreadPool.submit(r2);
        fixedThreadPool.submit(r3);
    }
}
