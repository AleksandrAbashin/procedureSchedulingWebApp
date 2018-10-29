package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.repos.StudyRepository;
import com.firstline.procedure.scheduling.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImp implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Override
    public void createStudy(StudyDto studyDto) {

    }

    @Override
    public void updateStudy(StudyDto studyDto) {

    }

    @Override
    public void deleteStudy(StudyDto studyDto) {

    }
}
