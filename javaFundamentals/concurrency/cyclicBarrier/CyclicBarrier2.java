package com.concurrency.cyclicBarrier;

import java.util.concurrent.*;

public class CyclicBarrier2 {

    private static BlockingQueue<Double> result = new LinkedBlockingQueue<>();

    // (432*3) + (3^14) + (45*127/12) = ?
    public static void main(String[] args) throws InterruptedException {
        Runnable sumResultExpression = () -> {
            double finalResult = result.stream().reduce(0d, Double::sum);
            System.out.println("sum of the expression: " + finalResult);
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, sumResultExpression);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        Runnable r1 = () -> {
            result.add(432d * 3d);
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        Runnable r2 = () -> {
            result.add(Math.pow(3, 14));
            await(cyclicBarrier);
            System.out.println("process finished");
        };
        Runnable r3 = () -> {
            result.add(45d * 127d / 12d);
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
