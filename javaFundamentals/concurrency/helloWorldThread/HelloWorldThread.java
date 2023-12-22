package com.concurrency.helloWorldThread;

public class HelloWorldThread extends Thread {

    @Override
    public void run() {
        System.out.println("Good morning, Thread");
    }

}
