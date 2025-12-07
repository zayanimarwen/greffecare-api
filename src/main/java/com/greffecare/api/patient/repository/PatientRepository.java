package com.greffecare.api.patient.repository;

import com.greffecare.api.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByNumeroDossier(String numeroDossier);

    boolean existsByNumeroDossier(String numeroDossier);
}

