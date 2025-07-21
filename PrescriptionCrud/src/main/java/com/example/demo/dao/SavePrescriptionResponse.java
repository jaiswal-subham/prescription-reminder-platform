package com.example.demo.dao;

import lombok.Data;

@Data
public class SavePrescriptionResponse {

    String status;

    public SavePrescriptionResponse(String status) {
        this.status = status;
    }
}
