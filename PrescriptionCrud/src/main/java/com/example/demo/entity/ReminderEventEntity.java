package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="reminder_outbox")
public class ReminderEventEntity  {

    @Id
    @GeneratedValue(generator = "uuid")  // 👈 tie it to custom generator
    @GenericGenerator(name = "uuid", strategy = "uuid2")  // 👈 generate a random UUID
    private String id;

    private String prescriptionId;

    private String userId;

    private  String prescriptionName;



    private LocalDateTime reminderTime;

    public ReminderEventEntity(String userId, LocalDateTime reminderTime,  String prescriptionName, String prescriptionId) {
        this.userId = userId;
        this.reminderTime = reminderTime;

        this.prescriptionName = prescriptionName;
        this.prescriptionId = prescriptionId;
    }
    public ReminderEventEntity(){}

}