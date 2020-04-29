package br.com.nando.arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LeftRoation {

    // Complete the rotLeft function below.
    //[1, 2, 3, 4, 5]  After 1 left rotation --> [2, 3, 4, 5, 1] After 2 left rotation --> [3, 4, 5, 1, 2]
    static int[] rotLeft(int[] a, int d) {
        int[] aux = new int[a.length];

        for (int i = 0; i < a.length; i++){aux[i] = a[i];}

        for(int i = 0; i <= a.length-d; i++){
            a[i] = aux[i+1];
        }

        for(int i = d; i < a.length; i++){
            a[i-d] = aux[i];
        }

        return a;
    }

    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
