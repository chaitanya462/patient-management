package com.pms.patientservice.controller;

import com.pms.patientservice.dto.PatientRequestDto;
import com.pms.patientservice.dto.PatientResponseDto;
import com.pms.patientservice.dto.validators.CreatePatientValidatorGroup;
import com.pms.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping("")
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidatorGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
