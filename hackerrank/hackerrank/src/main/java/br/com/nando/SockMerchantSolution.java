package br.com.nando;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SockMerchantSolution {


    /**
     * Solution using List and Map
     * @param n number os socks. n = 7 or 10.
     * @param ar array of int representing the colors. ar = [1 2 1 2 1 3 2] or [1 1 3 1 2 1 3 3 3 3].
     * @return numberOfPairs.
     */
    static int sockMerchant(int n, int[] ar){
        List<Integer> colors = new ArrayList<>();
        Map<Integer, Integer> colorFrequency = new HashMap<>();

        for(int color : ar){
            colors.add(color);
        }

        colors.forEach(color -> colorFrequency.put(color, Collections.frequency(colors, color)));
        Collection<Integer> values = colorFrequency.values();

        return values.stream().mapToInt(value -> (value / 2)).sum();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);
        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
