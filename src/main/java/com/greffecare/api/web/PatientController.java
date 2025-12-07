package com.greffecare.api.web;

import com.greffecare.api.domain.patient.Patient;
import com.greffecare.api.dto.PatientDto;
import com.greffecare.api.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getAll() {
        return patientService.findAll()
                .stream()
                .map(PatientDto::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                PatientDto.fromEntity(patientService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<PatientDto> create(@RequestBody Patient patient) {
        return ResponseEntity.ok(
                PatientDto.fromEntity(patientService.create(patient))
        );
    }
}
