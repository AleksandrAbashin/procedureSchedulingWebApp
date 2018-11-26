package com.firstline.service;

import com.firstline.domain.PatientInfo;

public interface PatientInfoService {
    public PatientInfo createPatientInfo(PatientInfo patientInfo);
    public PatientInfo updatePatientInfo(PatientInfo patientInfo);
    public PatientInfo getPatientInfoById(Long id);
}
