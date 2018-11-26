package com.firstline.service.impl;

import com.firstline.dto.StudyDto;
import com.firstline.exception.ThereIsNoSuchPatientException;
import com.firstline.mapper.StudyMapper;
import com.firstline.domain.Study;
import com.firstline.repos.PatientRepository;
import com.firstline.repos.StudyRepository;
import com.firstline.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImp implements StudyService {

    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public StudyDto createStudy(StudyDto studyDto) {
        Study study = studyMapper.toStudy(studyDto);

        if(studyDto.getPatientId() == null) {
            throw new ThereIsNoSuchPatientException();
        }

        study.setPatient(patientRepository.getById(studyDto.getPatientId()));
        studyRepository.save(study);
        return studyMapper.fromStudy(study);
    }

    @Override
    public StudyDto updateStudy(StudyDto studyDto) {
        Study study = studyMapper.toStudy(studyDto);

        if (studyDto.getPatientId() == null) {
            throw new NullPointerException();
        }

        return studyMapper.fromStudy(studyRepository.save(study));
    }

    @Override
    public void deleteStudy(Long id) {

    }

    public StudyDto getStudyById(Long id) {
        return studyMapper.fromStudy(studyRepository.getById(id));
    }
}
