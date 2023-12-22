package com.concurrency.exercises.ex03ServerFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerFileSolution {
    private static final String DIRECTORY_PATH = "E:\\github\\SOLID\\project\\files";

    public static void main(String[] args) {
        if (args.length > 0) {
            String command = args[0];
            System.out.println("Received command: " + command);

            if (command.equalsIgnoreCase("INDEX")) {
                List<String> file = readFile();
                System.out.println(file);
            } else if (command.equalsIgnoreCase("GET")) {
                System.out.println("not implemented");
            } else System.out.println("error");
        } else System.out.println("nothing to do");
    }

    private static List<String> readFile() {
        try {
            return Files
                    .lines(Paths.get(DIRECTORY_PATH + "\\file1.txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
