package com.example.demo.service;

import com.example.demo.configuration.CustomUser;
import com.example.demo.dao.SavePrescriptionRequest;
import com.example.demo.dao.SavePrescriptionResponse;
import com.example.demo.entity.PrescriptionEntity;
import com.example.demo.entity.ReminderEventEntity;
import com.example.demo.repo.PrescriptionRepo;
import com.example.demo.repo.ReminderEventRepo;
import com.example.demo.utilities.ConstantsValues;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PrescriptionService {

    ObjectMapper objectMapper;

    PrescriptionRepo prescriptionRepo;

    ReminderEventRepo reminderEventRepo;

    public PrescriptionService(ObjectMapper objectMapper, PrescriptionRepo prescriptionRepo,ReminderEventRepo reminderEventRepo) {
        this.objectMapper = objectMapper;
        this.prescriptionRepo = prescriptionRepo;
        this.reminderEventRepo = reminderEventRepo;
    }

    @Transactional
    public SavePrescriptionResponse savePrescription(@Valid SavePrescriptionRequest savePrescriptionRequest) throws JsonProcessingException {

        String prescriptionDataString =  objectMapper.writeValueAsString(savePrescriptionRequest.getPrescriptionData());
        CustomUser userDetails = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantsValues.dateTimeFormat);
        LocalDateTime dt = LocalDate.parse(savePrescriptionRequest.getReminderTime(), formatter).atStartOfDay();

        var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrescriptionEntity pe  = this.prescriptionRepo.save(new PrescriptionEntity(userDetails.getId(),prescriptionDataString,savePrescriptionRequest.getPrescriptionName(),savePrescriptionRequest.getNotes(),dt));

        this.reminderEventRepo.save(new ReminderEventEntity(userDetails.getId(), dt,savePrescriptionRequest.getPrescriptionName(),pe.getPrescriptionId()));
        return new SavePrescriptionResponse(ConstantsValues.success);
    }
}
