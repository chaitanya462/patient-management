package com.pms.patientservice.mapper;

import com.pms.patientservice.dto.PatientResponseDto;
import com.pms.patientservice.models.Patient;

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
}
