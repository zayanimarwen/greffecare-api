package com.greffecare.api.dto;

import com.greffecare.api.domain.patient.Patient;
import com.greffecare.api.domain.patient.PatientStatus;

import java.time.LocalDate;

public record PatientDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        PatientStatus status
) {
    public static PatientDto fromEntity(Patient p) {
        return new PatientDto(
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getBirthDate(),
                p.getStatus()
        );
    }
}

