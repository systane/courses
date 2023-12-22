package com.concurrency.executors;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorSingleThreadCallable {

    private static class MyRunnable implements Callable<String> {

        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            return "thread " + threadName + " with value " + new Random().nextInt(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = null;
        try {
            singleThreadExecutor = Executors.newSingleThreadExecutor();

            Future<String> future = singleThreadExecutor.submit(new MyRunnable());
            singleThreadExecutor.submit(new MyRunnable());

            System.out.println(future.isDone());
            System.out.println(future.get());
//            System.out.println(future.get(1, TimeUnit.SECONDS)); we can also use get with timeout
            System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (singleThreadExecutor != null) singleThreadExecutor.shutdownNow();
        }
    }

}
