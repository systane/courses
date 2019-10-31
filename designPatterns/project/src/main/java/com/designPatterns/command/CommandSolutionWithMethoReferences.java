package com.designPatterns.command;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class CommandSolutionWithMethoReferences {

    @FunctionalInterface
    //Command - Encapsulate all the information required for opening and saving a text file, including the receiver
    // object, the methods to call, and the arguments
    private interface TextFileOperation {
        String execute();
    }

    //ConcreteCommand A
    @AllArgsConstructor
    private static class OpenTextFileOperation implements TextFileOperation {
        private TextFile textFile;

        @Override
        public String execute() {
            return textFile.open();
        }
    }

    //ConcreteCommand B
    @AllArgsConstructor
    private static class SaveTextFileOperation implements TextFileOperation {
        private TextFile textFile;

        @Override
        public String execute() {
            return textFile.save();
        }
    }

    //Receiver - This class knows how to perform the operations associated with carrying out a request. Any class may serve as a Receiver
    @AllArgsConstructor
    private static class TextFile {
        private String name;

        private String open(){
            return "Opening file " + name;
        }

        private String save(){
            return "Saving file " + name;
        }
    }

    //Invoker
    private static class TextFileOperationExecutor {
        private final List<TextFileOperation> textFileOperations = new ArrayList<>();

        private String executeOperation(TextFileOperation textFileOperation){ //methos responsible for forwarding the request to the concrete class that will carry out the it.
            textFileOperations.add(textFileOperation);
            return textFileOperation.execute();
        }
    }

    //Client
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();

        TextFile textFile1 = new TextFile("file1.txt");
        TextFile textFile2 = new TextFile("file2.txt");

        System.out.println(textFileOperationExecutor.executeOperation(textFile1::open));
        System.out.println(textFileOperationExecutor.executeOperation(textFile2::save));

        //We separate the object how knows how to carry out the request from the one who invokes the operation.
    }
}
