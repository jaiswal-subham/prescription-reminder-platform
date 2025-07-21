package com.example.demo.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavePrescriptionRequest {

    private PrescriptionData prescriptionData;

    @NotBlank(message = "Prescription Name is required")
    private  String prescriptionName;

    @NotBlank(message = "Notes is required")
    private String notes;

    @NotBlank(message = "Reminder Time is required")
    private String reminderTime;

    public SavePrescriptionRequest(PrescriptionData prescriptionData, String notes, String prescriptionName, String reminderTime) {
        this.prescriptionData = prescriptionData;
        this.notes = notes;
        this.prescriptionName = prescriptionName;
        this.reminderTime = reminderTime;
    }

    public SavePrescriptionRequest() {
    }
}




