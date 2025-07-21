package com.example.demo.repo;

import com.example.demo.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepo extends JpaRepository<PrescriptionEntity,String> {


}

