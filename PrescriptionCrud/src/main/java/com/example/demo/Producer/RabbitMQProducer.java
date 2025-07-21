package com.example.demo.Producer;

import com.example.demo.configurationData.RabbitMQConfigurationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProducer {

    RabbitTemplate rabbitTemplate;

    RabbitMQConfigurationData rabbitMQConfigurationData;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate, RabbitMQConfigurationData rabbitMQConfigurationData) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfigurationData = rabbitMQConfigurationData;
    }

    public void sendMessage(String message){
        this.rabbitTemplate.convertAndSend(rabbitMQConfigurationData.getSaveReminderExchange(),rabbitMQConfigurationData.getSaveReminderRouting(),message);
    }

}
