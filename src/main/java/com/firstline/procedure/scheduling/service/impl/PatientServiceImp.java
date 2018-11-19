package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.exception.ThereIsNoSuchPatientNameException;
import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.mapper.PatientMapper;
import com.firstline.procedure.scheduling.mapper.StudyMapper;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    @Transactional
    public Page<PatientDto> paginatedList(Pageable pageable) {
        List<PatientDto> patients = patientMapper.fromListPatient(patientRepository.findAll());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PatientDto> list;

        if (patients.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, patients.size());
            list = patients.subList(startItem, toIndex);
        }

        Page<PatientDto> patientPage
                = new PageImpl<PatientDto>(list, PageRequest.of(currentPage, pageSize), patients.size());

        return patientPage;
    }

    @Override
    @Transactional
    public Page<PatientDto> pagination (Pageable pageable) {
        Page<Patient> patientsPage = patientRepository.findAll(pageable);
        List<PatientDto> patients = patientMapper.fromListPatient(patientsPage.getContent());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();

        Page<PatientDto> patientPage
                = new PageImpl<PatientDto>(patients, PageRequest.of(currentPage, pageSize), patients.size());

        return patientPage;
    }


    @Override
    @Transactional
    public List<StudyDto> getListStudiesOfPatient(Long id) {
        return studyMapper.fromListStudy(patientRepository.getById(id).getStudies());
    }

    @Override
    public Long createPatient(PatientDto patientDto) {
        if (patientDto.getPatientName() == null) {
            throw new ThereIsNoSuchPatientNameException();
        }

        Patient patient = patientMapper.toPatient(patientDto);

        return patientRepository.save(patient).getId();
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

    @Override
    @Transactional
    public PatientInfo getPatientInfoByPatientId(Long id) {
        return patientRepository.getById(id).getPatientInfo();
    }
}
