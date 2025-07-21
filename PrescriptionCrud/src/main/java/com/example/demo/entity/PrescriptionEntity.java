package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name="prescription")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(generator = "uuid")  // 👈 tie it to custom generator
    @GenericGenerator(name = "uuid", strategy = "uuid2")  // 👈 generate a random UUID
    private String prescriptionId;

    private String userId;

    private String prescriptionData;

    private  String prescriptionName;

    private String notes;

    private LocalDateTime reminderTime;

    public PrescriptionEntity(String userId, String prescriptionData, String prescriptionName, String notes,  LocalDateTime reminderTime) {
        this.userId = userId;
        this.prescriptionData = prescriptionData;
        this.prescriptionName = prescriptionName;
        this.notes = notes;
        this.reminderTime = reminderTime;
    }
}
