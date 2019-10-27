package com.designpatterns.bridge;

import lombok.AllArgsConstructor;

public class BridgeSolution {

    //Abstraction class
    @AllArgsConstructor
    private static abstract class Message {
        MessageSender messageSender;

        abstract public void sender();
    }

    //Implementor - abstraction to define the interface for the commom methods
    private interface MessageSender {
        void sendMMessage();
    }

    //RefinedAbstraction A
    private static class TextMessage extends Message {
        private TextMessage(MessageSender messageSender){
            super(messageSender);
        }

        public void sender() {
            messageSender.sendMMessage();
        }
    }

    //RefinedAbstraction B
    private static class EmailMessage extends Message {
        public EmailMessage(MessageSender messageSender){
            super(messageSender);
        }

        public void sender() {
            messageSender.sendMMessage();
        }
    }

    //ConcreteImplementorA
    private static class TextMessageSender implements MessageSender {
        public void sendMMessage() {
            System.out.println("TextMessageSender: Sending text message...");
        }
    }

    //ConcreteImplementorB
    private static class EmailMessageSender implements MessageSender {
        public void sendMMessage() {
            System.out.println("EmailMessageSender: Sending email message...");
        }
    }

    public static void main(String[] args) {
        //text
        MessageSender textMessageSender = new TextMessageSender();
        Message textMessage = new TextMessage(textMessageSender);
        textMessage.sender();

        //email
        MessageSender emailMessageSender = new EmailMessageSender();
        Message emailMessage = new EmailMessage(emailMessageSender);
        emailMessage.sender();
    }
}
