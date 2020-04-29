package com.example.consumerRabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableScheduling
public class ConsumerRabbitmqApplication {



	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(ConsumerRabbitmqApplication.class, args);
	}

}
