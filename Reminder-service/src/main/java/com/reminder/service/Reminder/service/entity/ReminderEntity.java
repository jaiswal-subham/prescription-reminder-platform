package com.reminder.service.Reminder.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="reminder")
public class ReminderEntity {

    @Id
    @GeneratedValue(generator = "uuid")  // 👈 tie it to custom generator
    @GenericGenerator(name = "uuid", strategy = "uuid2")  // 👈 generate a random UUID
    private String id;

    private String reminderEventId;

    private String prescriptionId;

    private String userId;

    private  String prescriptionName;

    private LocalDateTime reminderTime;

    private boolean sent;

    public ReminderEntity(String reminderEventId, String prescriptionId, String userId, boolean sent, LocalDateTime reminderTime, String prescriptionName) {
        this.reminderEventId = reminderEventId;
        this.prescriptionId = prescriptionId;
        this.userId = userId;
        this.sent = sent;
        this.reminderTime = reminderTime;
        this.prescriptionName = prescriptionName;
    }
}
