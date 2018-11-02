package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.Exception.ThereIsNoSuchPatientNameException;
import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.mapper.PatientMapper;
import com.firstline.procedure.scheduling.mapper.StudyMapper;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public Pair<Long, List<PatientDto>> getLimitLisOfPatient(int page, int size) {
        Page<Patient> pages = patientRepository.findAll(PageRequest.of(page, size));
        return Pair.of(pages.getTotalElements(),patientMapper.fromListPatient(pages.getContent()));
    }

    @Override
    @Transactional
    public List<StudyDto> getListStudiesOfPatient(Long id) {
        return studyMapper.fromListStudy(patientRepository.getById(id).getStudies());
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        if (patientDto.getPatientName() == null) {
            throw new ThereIsNoSuchPatientNameException();
        }

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
        return new PatientDto();
    }

    @Override
    @Transactional
    public List<PatientDto> getAllPatients() {
        return patientMapper.fromListPatient(patientRepository.findAll());
    }

    @Override
    @Transactional
    public PatientDto getPatientById(Long id) {
        return patientMapper.fromPatient(patientRepository.getOne(id));
    }
}
