package com.concurrency.synchronizedCollections;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SynchronizedCollectionMapProblem {

    static Map<Integer, String> map = new HashMap<>(); //shared resource between threads per jvm (static resource)

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);


        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);

        //there is no guarantee that our list is going to have 3 elements even we are using thread.sleep
        System.out.println(map);

    }


    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            map.put(new Random().nextInt(), "synchronized collection!");
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " inserted a new record in the map");
        }
    }
}
