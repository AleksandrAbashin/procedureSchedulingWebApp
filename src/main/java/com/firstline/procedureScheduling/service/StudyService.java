package com.firstline.procedureScheduling.service;


import com.firstline.procedureScheduling.domain.Study;
import org.springframework.beans.factory.annotation.Autowired;
import com.firstline.procedureScheduling.repos.StudyRepository;

public interface StudyService {

    public void createStudy(Study study);
    public void updateStudy(Study study);
    public void deleteStudy(Study study);
}

class StudyServiceImp implements StudyService {
    @Autowired
    StudyRepository studyRepository;

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
