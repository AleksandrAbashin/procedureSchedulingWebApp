package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.domain.PatientInfo;

public interface PatientInfoService {
    public PatientInfo createPatientInfo(PatientInfo patientInfo);
    public PatientInfo updatePatientInfo(PatientInfo patientInfo);
    public PatientInfo getPatientInfoById(Long id);
}
