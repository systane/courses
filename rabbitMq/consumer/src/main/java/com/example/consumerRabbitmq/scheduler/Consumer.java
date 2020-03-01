package com.example.consumerRabbitmq.scheduler;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private static final String QUEUE_NAME = "rabbitmqPocQueue";


    @Scheduled(cron = "30 * * * * *")
    public void consume() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicConsume(QUEUE_NAME, true,
                    (consumerTag, message) -> {
                        String messageConsumed = new String(message.getBody(), "UTF-8");
                        System.out.println("Message consumed: " + messageConsumed);
                    },
                    consumerTag -> {});
        }
    }
}
