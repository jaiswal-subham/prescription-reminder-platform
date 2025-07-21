package com.example.demo.controller;

import com.example.demo.dao.SavePrescriptionRequest;
import com.example.demo.dao.SavePrescriptionResponse;
import com.example.demo.service.PrescriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/prescription")
@RestController
public class PrescriptionController {


    PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescritionService) {
        this.prescriptionService = prescritionService;

    }

    @GetMapping("/health")
    String health() {
    return "All good";
    }

    @GetMapping("/admin/health")
    String adminHealth() {
        return "All good";
    }

    @PostMapping("/savePrescription")
    SavePrescriptionResponse SavePrescription(@Valid @RequestBody SavePrescriptionRequest savePrescriptionRequest) throws JsonProcessingException {
        return prescriptionService.savePrescription(savePrescriptionRequest);
    }


}
