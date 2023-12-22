package com.concurrency.exercises.ex02ProducerConsumerDeadLock;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDeadLockSolution1 {

    public static final BlockingQueue<Integer> LIST = new LinkedBlockingDeque<>(5);
    public static final Lock LOCK = new ReentrantLock();
    public static volatile boolean producing = true;
    public static volatile boolean consuming = true;

    /*
    Critical session: code region where variables are being accessed from different threads.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(() -> {
            try{
                while (true) {
                    simulateProcessing();
                    if(producing){
                        LOCK.lock();
                        System.out.println("Producing...");
                        int number = new Random().nextInt(10000);
                        LIST.add(number);
                        if(LIST.size() == 5) System.out.println("Pausing producer..."); producing = false;
                        if(LIST.size() == 1) System.out.println("Starting consumer..."); consuming = true;
                        LOCK.unlock();
                    } else System.out.println("!!!Producer is sleeping...");
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Thread consumer = new Thread(() -> {
            try{
                while (true) {
                    simulateProcessing();
                    if(consuming) {
                        LOCK.lock();
                        System.out.println("Consuming...");
                        Optional<Integer> firstNumberFound = LIST.stream().findFirst();
                        firstNumberFound.ifPresent(LIST::remove);
                        if(LIST.size() == 0) System.out.println("Pausing consumer..."); consuming = false;
                        if(LIST.size() == 4) System.out.println("Starting producer..."); producing = true;
                        LOCK.unlock();
                    } else System.out.println("???Consumer is sleeping...");
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        producer.start();
        consumer.start();
    }

    private static final void simulateProcessing() {
        int time = new Random().nextInt(4);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
