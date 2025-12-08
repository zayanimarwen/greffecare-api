package com.greffecare.api.patient.service;

import com.greffecare.api.patient.dto.PatientRequest;
import com.greffecare.api.patient.dto.PatientResponse;
import com.greffecare.api.patient.exception.PatientNotFoundException;
import com.greffecare.api.patient.model.Patient;
import com.greffecare.api.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

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

    // 🔸 NEW : mise à jour d'un patient existant
    public PatientResponse update(Long id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

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

    // 🔸 NEW : suppression d'un patient
    public void delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }

    private PatientResponse mapToResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getNom(),
                patient.getPrenom(),
                patient.getDateNaissance(),
                patient.getNumeroDossier(),
                patient.getEmail(),
                patient.getTelephone(),
                patient.getPathologie()
        );
    }
}

