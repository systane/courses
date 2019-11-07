package br.com.nando;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RepeatedString {

    // Complete the repeatedString function below.
    //aba
    //10
    //this method should return 7
    static long repeatedString(String s, long n) {
        Long quantityOfLetterA = createLetterSequence(s, n);

        return quantityOfLetterA;
    }

    //abaabaaba
    private static Long createLetterSequence(String s, long n) {
        Long multiplier = n/s.length();
        int quantityLetterToComplete = (int) n % s.length();
        Long count = 0L;

        for(char c : s.toCharArray()){
            if(c == 'a') count++;
        }

        count = count * multiplier;

        for(int i = 0; i < quantityLetterToComplete; i++) {
            if(s.charAt(i) == 'a') count++;
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
