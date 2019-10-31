package br.com.nando;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class EmailThreadSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        //int emailQuantity = scanner.nextInt();
        //scanner.nextLine();

        String [] emails = new String [5];
        //for(int i = 0; i < 3; i++){
            //String email = scanner.nextLine();
            emails[0] = "abc@gmail.com, x@gmail.com, hello x, how are you?";
            emails[1] = "c@gmail.com, abc@gmail.com, did you a look at the event?";
            emails[2] = "x@gmail.com, abc@gmail.com, I am great. how are you?----hello x, how are you?";
            emails[3] = "abc@gmail.com, x@gmail.com, I'am fine. I am great. how are you?----hello x, how are you?";
            emails[4] = "x@gmail.com, abc@gmail.com, Are you working? I'am fine. I am great.----how are you?----hello x, how are you?";

        //}

        //3
        //abc@gmail.com, x@gmail.com, hello x, how are you?
        //c@gmail.com, abc@gmail.com, did you a look at the event?
        //x@gmail.com, abc@gmail.com, I am great. how are you?----hello x, how are you?
        //[(1 1), (2 1) (1 2)


        //[{'john@gmail.com', 'susan@gmail.com', 'Are you back from vacation?'}, {'bob@gmail.com', 'alice@gmail.com', 'did you get the key?'}, {'susan@gmail.com', 'john@gmail.com', 'Just got in.----Are you back from vacation?'}]
        //Each email has threadId and emailNumber in that thread.
        //output should be [(1,1), (2,1), (1,2)] --> first email in thread 1 and its position is 1, the second email is in thread 2 and its position is 1, and the third email is in the thread 1, position 2.
        int[][] emailThreads = getEmailThreads(emails);
        printMatrix(emailThreads);

        scanner.close();
    }

    static int[][] getEmailThreads(String[] emails){
        int [][] output = new int[emails.length][emails.length];
        List<Email> emailList = new ArrayList<>();
        Set<String> emailKey =  new HashSet<>();
        Map<String, Integer> poolThread = new HashMap<>();
        for(String email : emails){
           emailList.add(getEmail(email));
        }


        int threadIdCounter = 1;
        List<InfoEmail> informacoes = new ArrayList<>();
        for (String emailInfo : emails) {
            int messageIdCounter = 1;
            Email emailToFind = getEmail(emailInfo);

//            long quantityMessageExchanged = emailList.stream()
//                    .filter(email -> (isFirstEmail(emailToFind, email) || isAReplyEmail(emailToFind, email)))
//                    .count();

            String hashIdentity = emailToFind.getEmailSender() + "-" + emailToFind.getEmailReceiver();
            InfoEmail infoEmail = new InfoEmail();

            if(poolThread.isEmpty()){
                poolThread.put(hashIdentity, threadIdCounter);

                infoEmail.setHashIdentify(hashIdentity);
                infoEmail.setThreadId(threadIdCounter);
                infoEmail.getEmailId().add(messageIdCounter);

            }
            else{
                if(!isHashIdentityAlreadyAdded(hashIdentity, poolThread)){
                    poolThread.put(hashIdentity, threadIdCounter++);

                    infoEmail.setHashIdentify(hashIdentity);
                    infoEmail.setThreadId(threadIdCounter);
                    infoEmail.getEmailId().add(messageIdCounter);
                }
                else{

                    infoEmail.setHashIdentify(hashIdentity);
                    infoEmail.setThreadId(getThreadId(poolThread, emailToFind));
                    messageIdCounter++;
                    infoEmail.getEmailId().add(messageIdCounter);
                }
            }

            informacoes.add(infoEmail);
        }


        return output;
    }

    private static Integer getThreadId(Map<String, Integer> poolThread, Email emailToFind) {
        Integer threadId = poolThread.get(emailToFind.getEmailSender() + "-" + emailToFind.getEmailReceiver());
        if(Objects.isNull(threadId)){
            threadId = poolThread.get(emailToFind.getEmailReceiver() + "-" + emailToFind.getEmailSender());
        }
        return threadId;
    }

    private static boolean isHashIdentityAlreadyAdded(String hashIdentity, Map<String, Integer> poolThread) {
        String sender = hashIdentity.split("-")[0];
        String receiver = hashIdentity.split("-")[1];

        Set<String> hashes = poolThread.keySet();
        return hashes.contains(sender + "-" + receiver) || hashes.contains(receiver + "-" + sender);
    }

    private static boolean isAReplyEmail(Email emailToFind, Email email) {
        return emailToFind.getEmailSender().equals(email.getEmailReceiver()) &&
                emailToFind.getEmailReceiver().equals(email.getEmailSender());
    }

    private static boolean isFirstEmail(Email emailToFind, Email email) {

        return emailToFind.getEmailSender().equals(email.getEmailSender()) &&
                emailToFind.getEmailReceiver().equals(email.getEmailReceiver());
    }

    private static boolean isEmailAlreadyAdded(Set<String> keys, Email email){
        return keys.stream().anyMatch(key -> key.equals(email.getEmailReceiver() + email.getEmailSender()) ||
                key.equals(email.getEmailSender() + email.getEmailReceiver()));
    }

    private static Email getEmail(String rawInfoRead) {
        Email email = new Email();

        String[] emailInfo = rawInfoRead.split(",");
        email.setEmailSender(emailInfo[0]);
        email.setEmailReceiver(emailInfo[1].trim());

        int blankspace = 3;
        int comma = 1;
        int beginMessage = email.getEmailSender().length() + email.getEmailReceiver().length() + blankspace + comma;
        email.setMessageEmail(rawInfoRead.substring(beginMessage));
        //abc@gmail.com, x@gmail.com, hello x, how are you?

        return email;
    }


    private static void printMatrix(int[][] emailThreads) {

    }


    public static class Email {
        private String emailSender;
        private String emailReceiver;
        private String messageEmail;
        private int threadId;
        private int emailNumber;

        public String getEmailSender() {
            return emailSender;
        }

        public void setEmailSender(String emailSender) {
            this.emailSender = emailSender;
        }

        public String getEmailReceiver() {
            return emailReceiver;
        }

        public void setEmailReceiver(String emailReceiver) {
            this.emailReceiver = emailReceiver;
        }

        public String getMessageEmail() {
            return messageEmail;
        }

        public void setMessageEmail(String messageEmail) {
            this.messageEmail = messageEmail;
        }

        public int getThreadId() {
            return threadId;
        }

        public void setThreadId(int threadId) {
            this.threadId = threadId;
        }

        public int getEmailNumber() {
            return emailNumber;
        }

        public void setEmailNumber(int emailNumber) {
            this.emailNumber = emailNumber;
        }
    }

    public static class InfoEmail {
        private String hashIdentify;
        private int threadId;
        private List<Integer> emailId = new ArrayList<>();

        public String getHashIdentify() {
            return hashIdentify;
        }

        public void setHashIdentify(String hashIdentify) {
            this.hashIdentify = hashIdentify;
        }

        public int getThreadId() {
            return threadId;
        }

        public void setThreadId(int threadId) {
            this.threadId = threadId;
        }

        public List<Integer> getEmailId() {
            return emailId;
        }

        public void setEmailId(List<Integer> emailId) {
            this.emailId = emailId;
        }
    }
}
