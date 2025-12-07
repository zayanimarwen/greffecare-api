package com.greffecare.api.repository;

import com.greffecare.api.domain.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

