package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.repos.StudyRepository;
import com.firstline.procedure.scheduling.service.StudyService;
import com.firstline.procedure.scheduling.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudyServiceImp implements StudyService {

    private final StudyRepository studyRepository;

    @Override
    public void createStudy(Study study) {
        studyRepository.save(study);

    }

    @Override
    public void updateStudy(Study study) {

    }

    @Override
    public void deleteStudy(Study study) {

    }
}
