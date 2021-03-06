package br.com.nando.problemSolving;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DiagonalDifference {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     * 1 2 3
     * 4 5 6
     * 9 8 9
     * 1+5+9 = 15; 3+5+9 = 17; |15-17| = 2
     */

    private static int diagonalDifference(List<List<Integer>> arr) {
        int rightDiagonal = 0, leftDiagonal = 0;

        for(int row = 0; row < arr.size(); row++){
            List<Integer> rowElements = arr.get(row);
            rightDiagonal += rowElements.get(row);
            leftDiagonal += rowElements.get(arr.size()-1-row);
        }

        return Math.abs(rightDiagonal - leftDiagonal);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = DiagonalDifference.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
