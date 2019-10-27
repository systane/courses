package com.designpatterns.bridge;

public class BridgeProblem {

    private static abstract class Message {
        //atributos: destinatário, remetente e mensagem a ser enviada

        abstract void sendMMessage();
    }

    private static abstract class TextMessage extends Message {
        abstract void sendMMessage();

        boolean validaDestinario() {//valida destinatário (número de celular)
            return true;
        }
    }

    private static abstract class EmailMessage extends Message {
        abstract void sendMMessage();

        boolean validaDestinario() {//valida destinatário (email)
            return true;
        }
    }

    private static class TextMessageSender extends TextMessage {
        @Override
        public void sendMMessage() {
            if(validaDestinario())
                System.out.println("TextMessageSender: Sending text message...");
        }
    }

    private static class EmailMessageSender extends EmailMessage {
        @Override
        public void sendMMessage() {
            if(validaDestinario())
                System.out.println("EmailMessageSender: Sending email message...");
        }
    }

    public static void main(String[] args) {
        Message textMessageSender = new TextMessageSender();
        textMessageSender.sendMMessage();

        Message emailMessageSender = new EmailMessageSender();
        emailMessageSender.sendMMessage();

        //PROBLEM: If we need to give the change to one of the abstract classes? We also would have to change the
        //concrete class.
    }
}
