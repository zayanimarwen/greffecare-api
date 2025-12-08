package com.greffecare.api.patient.web;

import com.greffecare.api.patient.dto.PatientRequest;
import com.greffecare.api.patient.dto.PatientResponse;
import com.greffecare.api.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.create(request));
    }

    // 🔸 NEW : update complet d'un patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(
            @PathVariable("id") Long id,
            @RequestBody PatientRequest request
    ) {
        return ResponseEntity.ok(patientService.update(id, request));
    }

    // 🔸 NEW : suppression d'un patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

