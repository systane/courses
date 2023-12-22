package com.concurrency.exercises.ex01LargestNumberDivisor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindLargestNumberOfDivisor implements Runnable {
    private final LargestNumberParameters parameters;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int lowerBound = parameters.getLowerBound();
        int upperBound = parameters.getUpperBound();
        System.out.println(threadName + " starting to calculate with lowerbound " + lowerBound + " upperbound " + upperBound);
        findLargestNumberOfDivisors(parameters);
        parameters.getLatch().countDown();
    }

    private void findLargestNumberOfDivisors(LargestNumberParameters parameters) {
        int divisorCount = 0;
        int maxDivisor = parameters.getLowerBound();
        int numberMaxDivisor = 1;

        for (int n = parameters.getLowerBound(); n <= parameters.getUpperBound(); n++) {
            for (int j = 1; j <= n; j++) {
                if (n % j == 0) divisorCount++;
            }

            if (divisorCount > maxDivisor) {
                maxDivisor = divisorCount;
                numberMaxDivisor = n;
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread " + threadName +
                    ", lowerbound " + parameters.getLowerBound() +
                    ", upperbound " + parameters.getUpperBound() +
                    ", numberMaxDivisor " + numberMaxDivisor);
        }
        parameters.getLargestNumberMap().putIfAbsent(numberMaxDivisor, maxDivisor);
    }
}