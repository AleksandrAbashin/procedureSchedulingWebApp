package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.dto.PatientDto;

public interface PatientService {

        public PatientDto createPatient(PatientDto patientDto);
        public PatientDto updatePatient(PatientDto patientDto);
        public PatientDto deletePatient(Long id);
}
