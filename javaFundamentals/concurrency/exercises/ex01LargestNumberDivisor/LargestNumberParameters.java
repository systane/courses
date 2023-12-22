package com.concurrency.exercises.ex01LargestNumberDivisor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

@Getter
@Setter
@AllArgsConstructor
public class LargestNumberParameters {

    private int lowerBound;
    private int upperBound;
    private ConcurrentMap<Integer, Integer> largestNumberMap;
    private CountDownLatch latch;

}
