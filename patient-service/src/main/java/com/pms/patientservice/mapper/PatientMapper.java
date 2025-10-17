package com.pms.patientservice.mapper;

import com.pms.patientservice.dto.PatientRequestDto;
import com.pms.patientservice.dto.PatientResponseDto;
import com.pms.patientservice.models.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto  getPatientDto(Patient p) {
        PatientResponseDto patientDto = new PatientResponseDto();
        patientDto.setId(String.valueOf(p.getId()));
        patientDto.setName(p.getName());
        patientDto.setAddress(p.getAddress());
        patientDto.setEmail(p.getEmail());
        patientDto.setDateOfBirth(p.getDateOfBirth().toString());
        return patientDto;
    }

    public static Patient getPatientEntity(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return patient;
    }
}
