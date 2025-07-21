package com.example.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        // Enable publisher confirms and returns
        rabbitTemplate.setMandatory(true); // Required for ReturnCallback to work

        rabbitTemplate.setReturnsCallback((ReturnedMessage returned) -> {
            // Message comes back from queue
            String message = "Message returned: " +
                    "\nExchange: " + returned.getExchange() +
                    "\nRouting Key: " + returned.getRoutingKey() +
                    "\nReply Code: " + returned.getReplyCode() +
                    "\nReply Text: " + returned.getReplyText() +
                    "\nMessage: " + new String(returned.getMessage().getBody());

            throw new RuntimeException(message);
        });

        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (!ack) {
                System.err.println("Message not acknowledged by broker: " + cause);
                throw new RuntimeException(cause);
            } else {
                System.out.println("Message sent successfully.");
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return new Jackson2JsonMessageConverter(mapper);
    }



}