package br.com.nando.warmUpChallenges;

import java.io.*;
import java.util.*;

public class JumpingOnTheClouds {

    // Complete the jumpingOnClouds function below.
    //7
    //0 0 1 0 0 1 0
    // min number of jumps to complete the path: 4. It's possible jump to next cloud (current index + 1) or current index + 2.
    static int jumpingOnClouds(int[] c) {
        int currentCloudIndex = 0;
        int minimumNumberOfJumps = 0;

        while (currentCloudIndex < c.length-1) {
            if (isNextCloudThunderheads(currentCloudIndex, c) || isNextOfTheNextCloudCumulus(currentCloudIndex, c)) { //proxima nuvem é thunderhead, logo preciso saltar 2 indíces no path.
                minimumNumberOfJumps++;
                currentCloudIndex += 2;
            } else if (isNextOfTheNextCloudThunderheads(currentCloudIndex, c)) {//a proxima da proxima nuvem é thunderhead, logo preciso saltar 1 indíce no path.
                minimumNumberOfJumps++;
                currentCloudIndex++;
            } else {
                minimumNumberOfJumps++;
                currentCloudIndex++;
            }
        }
        return minimumNumberOfJumps;
    }

    private static boolean isNextOfTheNextCloudThunderheads(int currentCloudIndex, int[] c) {
        int nextCloudIndex = currentCloudIndex + 1;

        return nextCloudIndex + 1 < c.length && isNextCloudThunderheads(nextCloudIndex, c);
    }

    private static boolean isNextOfTheNextCloudCumulus(int currentCloudIndex, int[] c) {
        return !isNextOfTheNextCloudThunderheads(currentCloudIndex, c);
    }

    private static boolean isNextCloudThunderheads(int cloudIndex, int[] c) {
        int thunderheads = 1;

        return cloudIndex + 1 < c.length && c[cloudIndex + 1] == thunderheads;
    }

    private static boolean isNextCloudCumulus(int cloudIndex, int[] c) {
        return !isNextCloudThunderheads(cloudIndex, c);
    }

    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        System.out.println("Minimum Number of Jumps: " + result);
        bufferedWriter.write("Minimum Number of Jumps: " + result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();

    }

}
