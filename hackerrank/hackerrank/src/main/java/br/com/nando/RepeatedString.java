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
        int quantityOfLetterA = 0;

        String multipliedString = createLetterSequence(s, n);

        for(int i = 0; i < multipliedString.length(); i++){
            if(multipliedString.charAt(i)  == 'a'){
                quantityOfLetterA++;
            }
        }

        return quantityOfLetterA;
    }

    //abaabaaba
    private static String createLetterSequence(String s, long n) {
        Long multiplier = n/s.length();
        Long quantityLetterToComplete = n % s.length();

        StringBuilder multipliedString = new StringBuilder();
        for(Long i = 0L; i < multiplier; i++){multipliedString.append(s);}

        for(Long i = 0L; i < quantityLetterToComplete; i++) {multipliedString.append((s.charAt(i.intValue())));}

        System.out.println("Infinity string: " + s);
        System.out.println("MultipliedString string: " + multipliedString.toString());

        return multipliedString.toString();
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
