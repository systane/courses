package com.concurrency.helloWorldSynchronize;

public class SynchronizedRealWorldExample {

    private static int i = 0; //shared resource between threads per jvm (static resource)

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);


        //there is no guarantee that the runnable code are going to be executed in sequence
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            int j;
            //only block the access to the shared resource
            synchronized (SynchronizedRealWorldExample.class) {
                i++;
                j = i * 2;
            }

            double jPow10 = Math.pow(j, 10);
            double sqrt = Math.sqrt(jPow10);
            System.out.println(sqrt);
        }
    }
}
