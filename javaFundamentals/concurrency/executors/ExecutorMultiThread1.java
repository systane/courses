package com.concurrency.executors;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorMultiThread1 {

    private static class MyRunnable implements Callable<String> {

        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            return "thread " + threadName + " with value " + new Random().nextInt(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorThreadPool = null;

        try {
//            executorThreadPool = Executors.newFixedThreadPool(4); fixed number of threads;

            //create a new thread only if necessary, else utilize an existing one. For this method, there is no limit of threads
            executorThreadPool = Executors.newCachedThreadPool();
            Future<String> f1 = executorThreadPool.submit(new MyRunnable());
            System.out.println(f1.get());

            Future<String> f2 = executorThreadPool.submit(new MyRunnable());
            Future<String> f3 = executorThreadPool.submit(new MyRunnable());
            System.out.println(f2.get());
            System.out.println(f3.get());
            executorThreadPool.shutdown();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (executorThreadPool != null) executorThreadPool.shutdownNow();
        }
    }
}
