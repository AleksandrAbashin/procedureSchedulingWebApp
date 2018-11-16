package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.repos.PatientInfoRepository;
import com.firstline.procedure.scheduling.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoServiceImp implements PatientInfoService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

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
