package com.example.demo.schedulejobs;

import com.example.demo.Producer.RabbitMQProducer;
import com.example.demo.entity.ReminderEventEntity;
import com.example.demo.repo.ReminderEventRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublishRemindersToRabbitMQ {

    private final RabbitMQProducer rabbitMQProducer;
    private final ReminderEventRepo reminderEventRepo;
    private final ObjectMapper objectMapper;

    public PublishRemindersToRabbitMQ(RabbitMQProducer rabbitMQProducer, ReminderEventRepo reminderEventRepo,ObjectMapper objectMapper) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.reminderEventRepo = reminderEventRepo;
        this.objectMapper = objectMapper;
    }

    // ✅ This method is locked/scheduled, no assert here

    @SchedulerLock(name = "publishReminderEvents")
    @Scheduled(cron = "0 * * * * *") // every 1 minute
    public void scheduledPublishEvents() throws Exception {
        publishEvents(); // no assert here
    }

    // ✅ This method is shared — can be tested or called manually too
    public void publishEvents() throws Exception {

        try {
            LockAssert.assertLocked();
        } catch (IllegalStateException e) {
            System.err.println("ShedLock is not active. Skipping execution.");
            return;
        }
        System.out.println("publishEvents");
        List<ReminderEventEntity> all = reminderEventRepo.findAll();
        for (ReminderEventEntity event : all) {
            try {
                String json = this.objectMapper.writeValueAsString(event);
                rabbitMQProducer.sendMessage(json);
                reminderEventRepo.deleteById(event.getId());
            } catch (Exception e) {
                System.err.println("Failed to publish event ID: " + event.getId() + " | " + e.getMessage());
            }
        }
    }
}
