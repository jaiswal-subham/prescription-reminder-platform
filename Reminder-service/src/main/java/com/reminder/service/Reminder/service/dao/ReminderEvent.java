package com.reminder.service.Reminder.service.dao;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReminderEvent {

    private String id;

    private String prescriptionId;

    private String userId;

    private  String prescriptionName;

    private LocalDateTime reminderTime;


}