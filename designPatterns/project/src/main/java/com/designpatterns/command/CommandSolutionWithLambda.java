package com.designpatterns.command;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class CommandSolutionWithLambda {

    @FunctionalInterface
    //Command - Encapsulate all the information required for opening and saving a text file, including the receiver
    // object, the methods to call, and the arguments
    private interface TextFileOperation {
        String execute();
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

        //Because we have created a funcional interface, we can use lambda functions on our clients to achieve the same result
        System.out.println(textFileOperationExecutor.executeOperation(() -> "Opening file file1.txt"));
        System.out.println(textFileOperationExecutor.executeOperation(() -> "Saving file file2.txt"));

    }
}
