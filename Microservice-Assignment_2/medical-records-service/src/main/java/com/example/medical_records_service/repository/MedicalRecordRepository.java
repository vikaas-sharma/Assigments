package com.example.medical_records_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medical_records_service.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
}

