package com.concurrency.exercises.ex01LargestNumberDivisor;

import java.util.Scanner;

public class Executor {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String stringRead = scanner.nextLine();
        System.out.println("You typed " + stringRead);
        Thread.sleep(10000);
        new FindLargestNumberOfDivisorUseCase().execute();
    }

}
