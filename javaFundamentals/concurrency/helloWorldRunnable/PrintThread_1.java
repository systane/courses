package com.concurrency.helloWorldRunnable;

import java.time.LocalDateTime;

public class PrintThread_1 implements Runnable {
    String str;

    public PrintThread_1(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        for (; ; ) {
            String valueToPrint = str + " Thread name: " + Thread.currentThread().getName() + " Time: " + LocalDateTime.now();
            System.out.println(valueToPrint);
        }
    }
}

class TestConcurrency {
    public static void main(String[] args) {
        new Thread(new PrintThread_1("AAAAA")).start();
        new Thread(new PrintThread_1("BBBBB")).start();
    }
}
