package com.concurrency.exercises.ex01LargestNumberDivisor;

import java.util.concurrent.*;

public class FindLargestNumberOfDivisorUseCase {

    private static final ConcurrentMap<Integer, Integer> LARGEST_NUMBER_MAP = new ConcurrentHashMap<>();
    private static CountDownLatch latch;
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 100000;
    private static final int INTERVAL = 10000;

    public ConcurrentMap<Integer, Integer> execute() {
        int threadExecutions = MAXIMUM / INTERVAL;
        latch = new CountDownLatch(threadExecutions);
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        findLargestNumberOfDivisor(threadExecutions, newCachedThreadPool);

        return sumResultExpression(newCachedThreadPool);
    }

    private static void findLargestNumberOfDivisor(int threadExecutions, ExecutorService newCachedThreadPool) {
        int lowerBound = MINIMUM;
        int upperBound = INTERVAL;

        for (int i = 0; i < threadExecutions; i++) {
            LargestNumberParameters parameters = new LargestNumberParameters(lowerBound, upperBound,
                    LARGEST_NUMBER_MAP, latch);
            newCachedThreadPool.submit(new FindLargestNumberOfDivisor(parameters));
            lowerBound = upperBound;
            upperBound = upperBound + INTERVAL;
        }
    }

    private static ConcurrentMap<Integer, Integer> sumResultExpression(ExecutorService newCachedThreadPool) {
        while (true) {
            await();
            newCachedThreadPool.submit(new SumResultExpression(LARGEST_NUMBER_MAP));
            break;
        }
        newCachedThreadPool.shutdown();
        return LARGEST_NUMBER_MAP;
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
