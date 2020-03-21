package com.example.producerRabbitmq.controller;

import com.example.producerRabbitmq.config.RabbitMqConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@RestController("/")
public class ProducerController {

    private static final String QUEUE_NAME = "rabbitmqPocQueue";

    private final RabbitMqConfig rabbitMqConfig;

    public ProducerController(RabbitMqConfig rabbitMqConfig) {
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @PostMapping("producer")
    public void producer(@RequestBody MessageDTO message) throws IOException, TimeoutException, InterruptedException, ApiException {
        String newMessage = message.getMessage() + " time: " + LocalDateTime.now();

        System.out.println("message receive to put in the queue: " + newMessage);

        ConnectionFactory factoryConnection = rabbitMqConfig.connection();

        try (Connection connection = factoryConnection.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, newMessage.getBytes("UTF-8"));
        }
    }
}
