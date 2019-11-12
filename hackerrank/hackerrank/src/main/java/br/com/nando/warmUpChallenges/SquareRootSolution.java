package br.com.nando.warmUpChallenges;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class SquareRootSolution {
    private static final Scanner scanner = new Scanner(System.in);

    private static final double PRECISION = 0.00005;

    private static double S = 00.0;
    private static double E = 20.0;
    private static double M = S;

    public static void main(String[] args) {
        System.out.println("Digite o valor de X");
        int x = scanner.nextInt();
        E = (double) x;

        M = squareRoot(x);

        System.out.printf("%d: %.3f\n", x, M) ;
    }



    private static double squareRoot(int x){
        double difference = ((M * M) - x) * -1;

        while(difference > PRECISION){
            double interval = (E + S);
            M = interval /2;

            if((M * M) > x){
                E = M;
            }
            else {
                S = M;
            }

            difference = (M * M) - x;
            if(difference < 0){
                difference *= -1;
            }
        }

        return M;
    }
}
