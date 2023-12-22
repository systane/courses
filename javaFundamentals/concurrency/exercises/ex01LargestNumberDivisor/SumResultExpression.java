package com.concurrency.exercises.ex01LargestNumberDivisor;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@AllArgsConstructor
public class SumResultExpression implements Runnable {
    private final ConcurrentMap<Integer, Integer> largestNumberMap;

    @Override
    public void run() {
        Optional<Integer> numberMaxDivisor = largestNumberMap.keySet().stream().max(Comparator.naturalOrder());
        Integer maxDivisor = largestNumberMap.get(numberMaxDivisor.get());
        System.out.println("the number " + numberMaxDivisor.get() + " has " + maxDivisor + " divisors");
    }
}
