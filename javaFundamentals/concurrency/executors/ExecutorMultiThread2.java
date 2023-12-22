package com.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorMultiThread2 {

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            String threadName = Thread.currentThread().getName();
            return "thread " + threadName + " with value " + new Random().nextInt(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorThreadPool = null;

        try {
            executorThreadPool = Executors.newCachedThreadPool();
            ArrayList<Task> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) list.add(new Task());

            List<Future<String>> futures = executorThreadPool.invokeAll(list);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }

            String fistFutureReturn = executorThreadPool.invokeAny(list);
            System.out.println(fistFutureReturn);

            executorThreadPool.shutdown();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (executorThreadPool != null) executorThreadPool.shutdownNow();
        }
    }
}
