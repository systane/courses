package com.concurrency.volatileYield;

public class VolatileProblem1 {

    private static int number = 0;
    private static boolean ready = false;

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                //yield suggests to the cpu that there is no work to do, so cpu can execute another process.
                // This is a cpu choice, and when it happens, the current thread is paused
                Thread.yield();
            }
            System.out.println("number value:" + number);
        }
    }


    public static void main(String[] args) {
        Thread t0 = new Thread(new MyRunnable());
        t0.start();
        number = 42;
        ready = true;
    }

}
