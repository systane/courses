package br.com.nando.warmUpChallenges;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RepeatedString {

    /**
     *
     * @param s - String with lowercase English letters that can be repeated many times
     * @param n - integer number that represents the quantity of times that s will be repreated.
     * @return the number of occurrences of 'a' in the arrays.
     */

    //abaac --> length = 5
    //n = 2
    //multiplier = 2/5 = 0.2 = 0.
    //quantityLetterToComplete = 0
    //abaac|abaac --> 6a

    //abaac --> length = 5
    //n = 10
    // multiplier = 10/5 = 2
    //quantityLetterToComplete = 0
    //abaac|abaac --> 6a

    //aba --> length = 3
    //10
    //multiplier = 10/3 = 3.33 = 3.
    //quantityLetterToComplete = 1
    //aba|aba|aba|a --> 7a
    static long repeatedString(String s, long n) {
        if(!s.contains("a")) return 0;

        long multiplier = n/s.length();
        long quantityLetterToComplete = n % s.length();

        return ((countA(s, s.length()) * multiplier) + countA(s, quantityLetterToComplete));
    }

    private static int countA (String s, long length){
        int count = 0;
        int i = 0;

        while(i < length){
            if(s.charAt(i) == 'a') count++;
            i++;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        System.out.println("Result: " + result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
