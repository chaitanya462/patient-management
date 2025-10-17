package com.pms.patientservice.exception;

public class PatientDoesNotExistsException extends RuntimeException{
    public PatientDoesNotExistsException(String message){
        super(message);
    }
}
