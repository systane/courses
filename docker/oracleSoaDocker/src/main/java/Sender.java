import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Objects;

public class Sender {

    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
    public final static String CONNECTION_FACTORY="TestConnectionFactory";
    public final static String JNDI_QUEUE="TestQueue";

    private static InitialContext getInitialContext(String url) throws NamingException {
        Hashtable<String, String> env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);

        return new InitialContext(env);
    }

    public static void main(String[] args) {
        int port = 7001;
        String host = "localhost";
        String url = "t3://" + host + ":" + port;

        try{
            InitialContext initialContext = getInitialContext(url);
            Queue queue = (Queue) initialContext.lookup(JNDI_QUEUE);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext.lookup(CONNECTION_FACTORY);
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            boolean quitNow = false;

            do {
                System.out.println("Enter message or type 'quit' to quit: \n");

                BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
                String line = msgStream.readLine();

                if(Objects.nonNull(line) && line.trim().length() != 0){
                    quitNow = line.equalsIgnoreCase("quit");
                    if(!quitNow){
                        sendMessage(session.createTextMessage(line), connection, session, queue);
                    }
                }
            }while (!quitNow);
        } catch (NamingException e){
            e.printStackTrace();
        } catch (JMSException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(TextMessage message, QueueConnection connection, QueueSession session, Queue queue) throws JMSException {
        connection.start();
        session.createSender(queue).send(message);
        connection.close();
    }
}
