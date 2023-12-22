package com.concurrency.synchronizedCollections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SynchronizedCollectionListSolution2 {

    //shared resource between threads per jvm (static resource)
    //thread-safe list - Each time a new element is added to list,
    // this class creates a new copy of the list to add the new element.
    static List<String> list = new CopyOnWriteArrayList<>();

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
