package com.designpatterns.bridge;

import lombok.AllArgsConstructor;

public class BridgeSolution {

    //Implementor - abstraction to define the interface for the commom methods that represents the implementation that was
    //separated with the BridgePattern
    private interface MessageSender {
        void sendMMessage();
    }

    //Abstraction class
    @AllArgsConstructor
    private static abstract class Message {
        MessageSender messageSender;//reference to Implementor interface. This reference can be used in the
        //specialization Classes (RefinedAbstraction) to call the implementation of the methods.

        abstract public void sender();
    }

    //RefinedAbstraction A
    private static class TextMessage extends Message {
        private TextMessage(MessageSender messageSender){
            super(messageSender);
        }

        public void sender() {
            System.out.println("RefinedAbstractionA: TextMessage");
            messageSender.sendMMessage();//call method from implementation class
        }
    }

    //RefinedAbstraction B
    private static class EmailMessage extends Message {
        private EmailMessage(MessageSender messageSender){
            super(messageSender);
        }

        public void sender() {
            System.out.println("RefinedAbstractionB: EmailMessage");
            messageSender.sendMMessage();//call method from implementation class
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

        //SOLUtION: Because we have separated the abstraction from implementation, we can evolute these two classes
        //independently
    }
}
