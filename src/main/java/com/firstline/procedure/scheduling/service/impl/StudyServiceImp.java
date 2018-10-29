package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.Study;
import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.mapper.StudyMapper;
import com.firstline.procedure.scheduling.repos.StudyRepository;
import com.firstline.procedure.scheduling.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImp implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public StudyDto createStudy(StudyDto studyDto) {
        Study study = studyMapper.toStudy(studyDto);
        studyRepository.save(study);

        return studyMapper.fromStudy(study);
    }

    @Override
    public StudyDto updateStudy(StudyDto studyDto) {
        return null;
    }

    @Override
    public void deleteStudy(Long id) {

    }
}
