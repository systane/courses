package com.concurrency.executors;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorScheduledThread1 {

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            return "thread " + threadName + " with value " + new Random().nextInt(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledThreadPool = null;

        try {
            scheduledThreadPool = Executors.newScheduledThreadPool(3);
            ScheduledFuture<String> future = scheduledThreadPool.schedule(new Task(), 2, TimeUnit.SECONDS);
            System.out.println(future.get());
            scheduledThreadPool.shutdown();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (scheduledThreadPool != null) scheduledThreadPool.shutdownNow();
        }
    }
}
