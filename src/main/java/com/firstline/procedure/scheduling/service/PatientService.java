package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.dto.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    PatientDto updatePatient(PatientDto patientDto);

    PatientDto deletePatient(Long id);

    List<PatientDto> getAllPatients();
}
