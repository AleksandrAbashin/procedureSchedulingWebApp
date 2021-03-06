package com.firstline.service.impl;

import com.firstline.domain.Patient;
import com.firstline.domain.PatientInfo;
import com.firstline.domain.Study;
import com.firstline.dto.PatientDto;
import com.firstline.dto.StudyDto;
import com.firstline.exception.ThereIsNoSuchPatientNameException;
import com.firstline.mapper.PatientMapper;
import com.firstline.mapper.StudyMapper;
import com.firstline.repos.PatientRepository;
import com.firstline.service.PatientService;
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

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private final StudyMapper studyMapper;

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, PatientMapper patientMapper, StudyMapper studyMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.studyMapper = studyMapper;
    }

    @Override
    @Transactional
    public Patient getByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
  //  @Transactional
    public List<Study> getListStudiesByPatientId(Long id) {
        return patientRepository.getListStudyByPatientId(id);
    }

    @Override
    @Transactional
    public Page<PatientDto> paginatedList(Pageable pageable) {
        List<PatientDto> patients = patientMapper
               // .fromListPatient(patientRepository.findAllPatient());
                .fromListPatient(patientRepository.findAll());
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
    public List<Patient> findAllPatient() {
        return patientRepository.findAllPatient();
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
