package com.designpatterns.bridge;

public class BridgeProblem {

    private interface Message {
        void sendMMessage();
    }

    private static class TextMessage implements Message {
        @Override
        public void sendMMessage() {
            System.out.println("We're going to send a TextMessage");
        }
    }

    private static abstract class EmailMessage implements Message {
        @Override
        public void sendMMessage() {
            System.out.println("We're going to send a EmailMessage");
        }
    }

    private static class TextMessageSender extends TextMessage {
        @Override
        public void sendMMessage() {
            super.sendMMessage();
            System.out.println("TextMessageSender: Sending text message...");
        }
    }

    private static class EmailMessageSender extends EmailMessage {
        @Override
        public void sendMMessage() {
            super.sendMMessage();
            System.out.println("EmailMessageSender: Sending email message...");
        }
    }

    public static void main(String[] args) {
        Message textMessageSender = new TextMessageSender();
        textMessageSender.sendMMessage();

        Message emailMessageSender = new EmailMessageSender();
        emailMessageSender.sendMMessage();

        //PROBLEM: If we need to give the change to one of the abstract classes? We also would have to change the
        //concrete class. How can we have a more flexible relationship? How can we evolute independently the interface
        //and the implementation classes?
    }
}
