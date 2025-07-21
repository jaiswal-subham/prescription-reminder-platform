package com.example.demo.configuration;

import com.example.demo.configurationData.RabbitMQConfigurationData;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final RabbitMQConfigurationData config;

    public RabbitMQConfig(RabbitMQConfigurationData config) {
        this.config = config;
    }

    // Main Exchange
    @Bean
    public TopicExchange mainExchange() {
        return new TopicExchange(config.getSaveReminderExchange());
    }

    // DLX Exchange
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(config.getDlxExchange());
    }

    // Main Queue
    @Bean
    public Queue mainQueue() {
        return QueueBuilder.durable(config.getSaveReminderQueue())
                .withArgument("x-dead-letter-exchange", config.getDlxExchange())
                .withArgument("x-dead-letter-routing-key", config.getRetryRouting())
                .build();
    }

    // Retry Queue
    @Bean
    public Queue retryQueue() {
        return QueueBuilder.durable(config.getRetryQueue())
                .withArgument("x-dead-letter-exchange", config.getSaveReminderExchange())
                .withArgument("x-dead-letter-routing-key", config.getSaveReminderRouting())
                .withArgument("x-message-ttl", 60000) // Retry after 1 hour
                .build();
    }

    // Bind mainQueue to mainExchange
    @Bean
    public Binding mainQueueBinding() {
        return BindingBuilder
                .bind(mainQueue())
                .to(mainExchange())
                .with(config.getSaveReminderRouting());
    }

    // Bind retryQueue to DLX Exchange
    @Bean
    public Binding retryQueueBinding() {
        return BindingBuilder
                .bind(retryQueue())
                .to(dlxExchange())
                .with(config.getRetryRouting());
    }
}
