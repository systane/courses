package com.concurrency.exercises.ex02ProducerConsumerDeadLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ProducerConsumerDeadLockProblem {

    public static final List<Integer> LIST = new ArrayList<>(5);
    public static boolean producing = true;
    public static boolean consuming = true;

    /*
    Deadlock between producer and consumer can occur when the consumer is executing and tries to deactivate himself
    (list size = 0), but before the consumer execute this command its thread is paused by cpu while the producer is executing.
    The produces notice that the consumer is paused due the list size = 1 (he just has produced a record), and then he
    tries to activate the consumer and his command is executed by cpu first than the consumer command (stopping consuming).
    This situation is going to lead to deadlock due to a race condition. Both thread are locked in the same state, because
    one is waiting for another.
     */

    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(() -> {
            try{
                while (true) {
                    simulateProcessing();
                    if(producing){
                        System.out.println("Producing...");
                        int number = new Random().nextInt(10000);
                        LIST.add(number);
                        if(LIST.size() == 5) {System.out.println("Pausing producer..."); producing = false;}
                        if(LIST.size() == 1) {System.out.println("Starting consumer..."); consuming = true;}
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
                        System.out.println("Consuming...");
                        Optional<Integer> firstNumberFound = LIST.stream().findFirst();
                        firstNumberFound.ifPresent(LIST::remove);
                        if(LIST.size() == 0) {System.out.println("Pausing consumer..."); consuming = false;}
                        if(LIST.size() == 4) {System.out.println("Starting producer..."); producing = true;}
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
