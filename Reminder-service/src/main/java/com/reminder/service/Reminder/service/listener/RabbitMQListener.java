package com.reminder.service.Reminder.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reminder.service.Reminder.service.configurationData.RabbitMQConfigurationData;
import com.reminder.service.Reminder.service.dao.ReminderEvent;
import com.reminder.service.Reminder.service.entity.ReminderEntity;
import com.reminder.service.Reminder.service.repo.ReminderRepo;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class RabbitMQListener {

    RabbitMQConfigurationData rabbitMQConfigurationData;

    ReminderRepo reminderRepo;

    ObjectMapper objectMapper;


    public RabbitMQListener(RabbitMQConfigurationData rabbitMQConfigurationData,ReminderRepo reminderRepo,ObjectMapper objectMapper) {
        this.rabbitMQConfigurationData = rabbitMQConfigurationData;
        this.reminderRepo = reminderRepo;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "#{rabbitMQConfigurationData.saveReminderQueue}")
    public void handleMessage(String message) {
        try {


            ReminderEvent  reminderEvent =  this.objectMapper.readValue(message, ReminderEvent.class);
            ReminderEntity reminderEntity = new ReminderEntity(reminderEvent.getId(),reminderEvent.getPrescriptionId(),reminderEvent.getUserId(),false,reminderEvent.getReminderTime(),reminderEvent.getPrescriptionName());

            Optional<ReminderEntity> optionalReminderEntity = this.reminderRepo.findByReminderEventId(reminderEvent.getId());

            if(optionalReminderEntity.isPresent()) return;
            else {
                this.reminderRepo.save(reminderEntity);
            }

        } catch (Exception ex) {
            System.out.println("Failed to process RabbitMQ message: {}");
            System.out.println(ex);
            System.out.println(ex.getMessage());
            // Rethrow to trigger DLX logic

        throw new AmqpRejectAndDontRequeueException("Send to DLQ", ex);
        }
    }





}
