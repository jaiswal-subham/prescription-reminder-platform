package com.reminder.service.Reminder.service.configurationData;

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


}

