package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.mapper.PatientMapper;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImp implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientMapper patientMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        Patient patient = patientMapper.toPatient(patientDto);
        patientRepository.save(patient);

        return patientMapper.fromPatient(patient);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
return new PatientDto();
    }

    @Override
    public PatientDto deletePatient(Long id) {
        patientRepository.deleteById(id);
        return new  PatientDto();
    }
}
