package com.example.demo.configurationData;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfigurationData {

    private String saveReminderQueue;
    private String saveReminderExchange;
    private String saveReminderRouting;

    // Add these for retry/DLX
    private String retryQueue;
    private String retryRouting;
    private String dlxExchange;


}
