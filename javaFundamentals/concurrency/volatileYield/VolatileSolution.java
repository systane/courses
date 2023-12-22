package com.concurrency.volatileYield;

public class VolatileSolution {

    //volatile: keyword that indicates to cpu read the variable value from memory (most updated value) instead of a register
    //from cpu.
    private static volatile int number = 0;
    private static volatile boolean ready = false;

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                //yield suggests to the cpu that there is no work to do, so cpu can execute another process.
                // This is a cpu choice, and when it happens, the current thread is paused
                Thread.yield();
            }

            if (number != 42) throw new IllegalStateException("error");
        }
    }


    public static void main(String[] args) {
        while (true) {
            Thread t0 = new Thread(new MyRunnable());
            t0.start();
            Thread t1 = new Thread(new MyRunnable());
            t1.start();
            Thread t2 = new Thread(new MyRunnable());
            t2.start();

            number = 42;
            ready = true;

            while (t0.getState() != Thread.State.TERMINATED ||
                    t1.getState() != Thread.State.TERMINATED ||
                    t2.getState() != Thread.State.TERMINATED) {
                //waiting
            }

            number = 0;
            ready = false;
        }


    }

}
