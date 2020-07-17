package br.com.nando;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EmailThreadSolution2 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DASH = "---";
    private static final int FIRST_MESSAGE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int emailsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> emails = IntStream.range(0, emailsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<List<Integer>> result = getEmailThreads(emails);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<List<Integer>> getEmailThreads(List<String> emails) {
        List<EmailInfo> emailList = getListOfEmails(emails);

        Map<String, Integer> poolThread = new HashMap<>();
        int threadIdCounter = 1;

        for(EmailInfo email : emailList) {
            if(Objects.nonNull(email.getMessageEmail())){
                if(!hasDashInBodyMessage(email)){
                    String hashIdentity = email.getMessageEmail();

                    if(!isHashAlreadyAdded(hashIdentity, poolThread)){
                        email.setEmailNumber(FIRST_MESSAGE);
                        poolThread.put(hashIdentity, threadIdCounter);
                        email.setThreadId(threadIdCounter);
                        threadIdCounter++;
                    }
                }
                else {
                    for(EmailInfo previousMessage : emailList){
                        if(Objects.nonNull(previousMessage.getMessageEmail())){
                            if(email.getMessageEmail().contains(previousMessage.getMessageEmail()) ){
                                String[] message = email.getMessageEmail().split(DASH);
                                String hashIdentity = message[message.length];
                                int emailThread = getEmailThread(hashIdentity, poolThread);
                                email.setThreadId(emailThread);
                                int emailNumber = getEmailNumber(email);
                                email.setEmailNumber(emailNumber);
                            }
                        }
                    }
                }
            }
        }

        return formatOutput(emailList);

    }

    private static List<EmailInfo> getListOfEmails(List<String> emails) {
        List<EmailInfo> emailList = new ArrayList<>();
        for (String stringEmail : emails) {
            emailList.add(getEmail(stringEmail));
        }
        return emailList;
    }

    private static List<List<Integer>> formatOutput(List<EmailInfo> emailList) {
        List<List<Integer>> result = new ArrayList<>();

        for(EmailInfo emailInfo : emailList){
            result.add(Arrays.asList(emailInfo.getThreadId(), emailInfo.getEmailNumber()));

            System.out.println("threadId: " + emailInfo.getThreadId() + " " + "EmailNumber: " + emailInfo.getEmailNumber());
        }
        return result;
    }

    private static boolean isHashAlreadyAdded(String hashIdentity, Map<String, Integer> poolThread) {
        return poolThread.containsKey(hashIdentity);
    }

    private static int getEmailNumber(EmailInfo email) {
        String[] exchangedMessages = email.getMessageEmail().split(DASH);
        return exchangedMessages.length;
    }

    private static int getEmailThread(String hashIdentity, Map<String, Integer> poolThread) {
        return poolThread.get(hashIdentity);
    }

    private static boolean hasDashInBodyMessage(EmailInfo email) {
        return email.getMessageEmail().contains(DASH);
    }

    private static EmailInfo getEmail(String rawInfoRead) {
        EmailInfo email = new EmailInfo();

        if(!rawInfoRead.isEmpty()){
            String[] emailInfo = rawInfoRead.split(",");
            email.setEmailSender(emailInfo[0].trim());
            email.setEmailReceiver(emailInfo[1].trim());
            int blankspace = 2;
            int comma = 2;
            int message = email.getEmailSender().length() + email.getEmailReceiver().length() + blankspace + comma;
            email.setMessageEmail(rawInfoRead.substring(message));

        }
        return email;
    }

    public static class EmailInfo {
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

}
