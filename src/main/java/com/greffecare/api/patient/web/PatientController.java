package com.greffecare.api.patient.web;

import com.greffecare.api.patient.dto.PatientRequest;
import com.greffecare.api.patient.dto.PatientResponse;
import com.greffecare.api.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PatientResponse create(@RequestBody PatientRequest request) {
        return patientService.create(request);
    }

    @GetMapping
    public List<PatientResponse> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public PatientResponse getById(@PathVariable("id") Long id) {
        return patientService.getById(id);
    }
}

