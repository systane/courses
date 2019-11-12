package br.com.nando.warmUpChallenges;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountingVallyes {

    // Complete the countingValleys function below.
    //Example of input:
    //8
    //UDDDUDUU
    //DDUU
    //This method must return the number of vallyes, in this example 1 valley
    static int countingValleys(int n, String s) {
        int numberOfValleys = 0;
        int altitude = 0;

        for(char step : s.toCharArray()){
            if(step == 'D')
                altitude--;
            else
                altitude++;

            if(altitude == 0 && step == 'U')
                numberOfValleys++;
        }
        return numberOfValleys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
