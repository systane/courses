package com.concurrency.helloWorldRunnable;

public class HelloWorldRunnable implements Runnable {


    @Override
    public void run() {
        System.out.println("Good morning, Runnable");
    }
}
