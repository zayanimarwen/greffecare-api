package com.greffecare.api.patient.dto;

import java.time.LocalDate;

public record PatientResponse(
        Long id,
        String nom,
        String prenom,
        LocalDate dateNaissance,
        String numeroDossier,
        String email,
        String telephone,
        String pathologie
) {}


