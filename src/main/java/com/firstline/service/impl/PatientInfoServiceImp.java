package com.firstline.service.impl;

import com.firstline.domain.PatientInfo;
import com.firstline.repos.PatientInfoRepository;
import com.firstline.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoServiceImp implements PatientInfoService {

    private final PatientInfoRepository patientInfoRepository;

    @Autowired
    public PatientInfoServiceImp(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }

    @Override
    public PatientInfo createPatientInfo(PatientInfo patientInfo) {
        return patientInfoRepository.save(patientInfo);
    }

    @Override
    public PatientInfo updatePatientInfo(PatientInfo patientInfo) {
        return null;
    }

    @Override
    public PatientInfo getPatientInfoById(Long id) {
        return null;
    }
}
