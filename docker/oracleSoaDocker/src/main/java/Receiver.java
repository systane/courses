import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Receiver implements MessageListener {
    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
    public final static String CONNECTION_FACTORY="TestConnectionFactory";
    public final static String JNDI_QUEUE="TestQueue";


    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try{
                String textMessage = ((TextMessage) (message)).getText();
                System.out.printf("The message received: " + textMessage + "\n");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

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


            Receiver receiver = new Receiver();
            connection.start();
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(receiver);


        } catch (NamingException e){
            e.printStackTrace();
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
