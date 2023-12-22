package com.concurrency.helloWorldRunnable;

public class TestHelloWorldRunnable {

    public static void main(String[] args) {
        Thread newThread = new Thread(new HelloWorldRunnable());
        newThread.start();
        System.out.println("The name of the main thread of our application is: " + Thread.currentThread().getName());
        System.out.println("The name of the secondary thread of our application is: " + newThread.getName());
    }
}
