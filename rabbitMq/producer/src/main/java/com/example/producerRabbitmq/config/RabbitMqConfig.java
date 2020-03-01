package com.example.producerRabbitmq.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConfig {

    public ConnectionFactory connection(){
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        return factory;
    }
}
