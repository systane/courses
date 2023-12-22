package com.concurrency.helloWorldRunnable;

public class ThreadWithLambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Hello world thread with lambda")).start();
    }
}