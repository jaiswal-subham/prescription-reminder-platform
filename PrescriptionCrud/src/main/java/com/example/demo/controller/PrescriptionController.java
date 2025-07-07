package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/prescription")
@RestController
public class PrescriptionController {

    @GetMapping("/health")
    String health() {
    return "All good";
    }

}
