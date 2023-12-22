package com.concurrency.synchronizedCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectionListSolution1 {

    //shared resource between threads per jvm (static resource)
    //thread-safe list - wrapper around add method, in other words, it adds a synchronized keyword in add method from list class
    static List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);


        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);

        System.out.println(list);

    }


    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            list.add("synchronized collection!");
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " inserted a new record in the list");
        }
    }
}
