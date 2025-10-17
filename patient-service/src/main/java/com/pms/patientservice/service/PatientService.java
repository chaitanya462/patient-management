package com.pms.patientservice.service;

import com.pms.patientservice.dto.PatientRequestDto;
import com.pms.patientservice.dto.PatientResponseDto;
import com.pms.patientservice.exception.EmailAlreadyExistsException;
import com.pms.patientservice.exception.PatientDoesNotExistsException;
import com.pms.patientservice.mapper.PatientMapper;
import com.pms.patientservice.models.Patient;
import com.pms.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

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
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists : " + patientRequestDto.getEmail());
        }
        return PatientMapper.getPatientDto(patientRepository.save(PatientMapper.getPatientEntity(patientRequestDto)));
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientDoesNotExistsException("Patient with id : " + id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email already exists : " + patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        return PatientMapper.getPatientDto(patientRepository.save(patient));
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
