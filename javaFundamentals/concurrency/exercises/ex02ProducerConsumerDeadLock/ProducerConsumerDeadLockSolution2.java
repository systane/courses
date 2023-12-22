package com.concurrency.exercises.ex02ProducerConsumerDeadLock;

import java.util.Random;
import java.util.concurrent.*;

public class ProducerConsumerDeadLockSolution2 {

    public static final BlockingQueue<Integer> LIST = new LinkedBlockingDeque<>(5);

    public static void main(String[] args) throws InterruptedException {

        Runnable producer = () -> {
            simulateProcessing();
            System.out.println("Producing...");
            int number = new Random().nextInt(10000);
            try{
                LIST.put(number);
            }catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        };

        Runnable consumer = () -> {
            simulateProcessing();
            System.out.println("Consuming...");
            try{
                LIST.take();
            }catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        };

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleWithFixedDelay(producer, 0, 10, TimeUnit.MILLISECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(consumer, 0, 10, TimeUnit.MILLISECONDS);
    }

    private static void simulateProcessing() {
        int time = new Random().nextInt(4);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
