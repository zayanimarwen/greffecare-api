package com.greffecare.api.patient.service;

import com.greffecare.api.patient.dto.PatientRequest;
import com.greffecare.api.patient.dto.PatientResponse;
import com.greffecare.api.patient.exception.PatientNotFoundException;
import com.greffecare.api.patient.model.Patient;
import com.greffecare.api.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientResponse create(PatientRequest request) {
        Patient patient = new Patient();
        patient.setNom(request.nom());
        patient.setPrenom(request.prenom());
        patient.setDateNaissance(request.dateNaissance());
        patient.setNumeroDossier(request.numeroDossier());
        patient.setEmail(request.email());
        patient.setTelephone(request.telephone());
        patient.setPathologie(request.pathologie());

        Patient saved = patientRepository.save(patient);
        return mapToResponse(saved);
    }

    public List<PatientResponse> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PatientResponse getById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        return mapToResponse(patient);
    }

    private PatientResponse mapToResponse(Patient p) {
        return new PatientResponse(
                p.getId(),
                p.getNom(),
                p.getPrenom(),
                p.getDateNaissance(),
                p.getNumeroDossier(),
                p.getEmail(),
                p.getTelephone(),
                p.getPathologie()
        );
    }
}

