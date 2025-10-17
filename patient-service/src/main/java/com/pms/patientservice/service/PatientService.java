package com.pms.patientservice.service;

import com.pms.patientservice.dto.PatientRequestDto;
import com.pms.patientservice.dto.PatientResponseDto;
import com.pms.patientservice.mapper.PatientMapper;
import com.pms.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatients() {
        return patientRepository.findAll().stream().map(PatientMapper::getPatientDto).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        return PatientMapper.getPatientDto(patientRepository.save(PatientMapper.getPatientEntity(patientRequestDto)));
    }
}
