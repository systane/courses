package br.com.nando.warmUpChallenges;

import java.util.*;

public class KeyToDecodeMessageSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < t; i++){
            String line = scanner.nextLine();

            String decodedMessage = decodeMessage(line);

            System.out.println(decodedMessage);
        }
    }

    //case test
    //3 onetwoone
    //3 believeyoucanandyouarehalfwaythere
    //2 courtesyisasmuchamarkofagentlemanascourageasbraveness
    //1 courtesyisasmuchamarkofagentlemanascourageasbraveness
    //4 testingthecodetofindtheerrortestandtestagain
    //10 weatgeekhunterhelpyoufindingtopgeekhunterjobs
    private static String decodeMessage(String line) {
        String[] lineInformation = line.split(" ");
        int n = Integer.valueOf(lineInformation[0]);
        String encodedMessage = lineInformation[1];

        List<String> combinations = new ArrayList<>();

        for(int i = 0; n+i <= encodedMessage.length(); i++){
            combinations.add(encodedMessage.substring(i, n+i));
        }

        Map<String, Integer> frequencyOfCode = new HashMap<>();
        combinations.forEach(combination -> frequencyOfCode.put(combination, Collections.frequency(combinations, combination)));
        Integer maxFrequency = frequencyOfCode.values().stream().max(Comparator.comparing(Integer::valueOf)).orElse(0);
        final String[] decodedMessage = {""};

        frequencyOfCode.forEach((s, integer) -> {
            if(integer.equals(maxFrequency)){
                decodedMessage[0] = s;
            }
        });

        return decodedMessage[0];
    }
}
