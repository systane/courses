package com.concurrency.exercises.ex01LargestNumberDivisor;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.*;

public class FindLargestNumberOfDivisorsOneClassSolution {

    private static final ConcurrentMap<Integer, Integer> LARGEST_NUMBER_MAP = new ConcurrentHashMap<>();
    private static CountDownLatch latch;
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 100000;
    private static final int INTERVAL = 50000;

    public static void main(String[] args) {
        int lowerBound = MINIMUM;
        int upperBound = MAXIMUM;
        int threadExecutions = MAXIMUM / INTERVAL;
        latch = new CountDownLatch(threadExecutions);

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.submit(new FindLargestNumberOfDivisorTask(1, 50000));
        newCachedThreadPool.submit(new FindLargestNumberOfDivisorTask(50000, 100000));
        while(true) {
            await();
            newCachedThreadPool.submit(new SumResultExpression());
            break;
        }
        newCachedThreadPool.shutdown();
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class FindLargestNumberOfDivisorTask implements Runnable {
        private final int lowerBound;
        private final int upperBound;

        public FindLargestNumberOfDivisorTask(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +
                    " starting to calculate with lowerbound " + lowerBound + " upperbound " + upperBound);
            findLargestNumberOfDivisors(lowerBound, upperBound);
            latch.countDown();
        }

        private static void findLargestNumberOfDivisors(int lowerBound, int upperBound) {
            int divisorCount = 0;
            int maxDivisor = lowerBound;
            int numberMaxDivisor = 1;

            for (int n = lowerBound; n <= upperBound; n++) {
                for (int j = 1; j <= n; j++) {
                    if (n % j == 0) divisorCount++;
                }

                if (divisorCount > maxDivisor) {
                    maxDivisor = divisorCount;
                    numberMaxDivisor = n;
                }
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread " + threadName +
                        ", lowerbound " + lowerBound +
                        ", upperbound " + upperBound +
                        ", numberMaxDivisor " + numberMaxDivisor);
            }
            LARGEST_NUMBER_MAP.putIfAbsent(numberMaxDivisor, maxDivisor);
        }
    }

    private static class SumResultExpression implements Runnable {

        @Override
        public void run() {
            Optional<Integer> numberMaxDivisor = LARGEST_NUMBER_MAP.keySet().stream().max(Comparator.naturalOrder());
            Integer maxDivisor = LARGEST_NUMBER_MAP.get(numberMaxDivisor.get());
            System.out.println("the number " + numberMaxDivisor.get() + " has " + maxDivisor + " divisors");
        }
    }


    private static void findLargestNumberOfDivisorsSingleThread() {
        int divisorCount = 0;
        int maxDivisor = 1;
        int numberMaxDivisor = 1;

        for (int n = 2; n <= 10000; n++) {
            for (int j = 1; j <= n; j++) {
                if (n % j == 0) divisorCount++;
            }

            if (divisorCount > maxDivisor) {
                maxDivisor = divisorCount;
                numberMaxDivisor = n;
            }
        }

        System.out.println("the number " + numberMaxDivisor + " has " + maxDivisor + " divisors");
    }

}
