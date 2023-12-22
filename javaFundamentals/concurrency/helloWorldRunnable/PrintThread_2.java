package com.concurrency.helloWorldRunnable;

import java.time.LocalDateTime;

public class PrintThread_2 implements Runnable {
    String str;

    public PrintThread_2(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        for (; ; ) {
            String valueToPrint = str + " Thread name: " + Thread.currentThread().getName() + " Time: " + LocalDateTime.now();
            System.out.println(valueToPrint);
            Thread.yield();
        }
    }
}

class TestConcurrency2 {
    public static void main(String[] args) {
        new Thread(new PrintThread_2("AAAAA")).start();
        new Thread(new PrintThread_2("BBBBB")).start();
    }
}
