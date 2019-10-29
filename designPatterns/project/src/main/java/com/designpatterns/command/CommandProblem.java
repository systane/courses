package com.designpatterns.command;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

public class CommandProblem {
    //Receiver
    @AllArgsConstructor
    private static class TextFile {
        private String open(String name){
            return "Opening file " + name;
        }

        private String save(String name){
            return "Saving file " + name;
        }
    }

    //Invoker
    private static class TextFileOperationExecutor {
        private TextFile textFile;

        private TextFileOperationExecutor(){
            textFile = new TextFile();
        }

        private String invokeOpen(String name){
            return textFile.open(name);
        }

        private String invokeSave(String name){
            return textFile.save(name);
        }
    }

    //Client
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();

        System.out.println(textFileOperationExecutor.invokeOpen("file1.txt"));
        System.out.println(textFileOperationExecutor.invokeSave("file2.txt"));

        //PROBLEM: How can we decouple the Receiver and Invoker class? If we need to change
    }
}
