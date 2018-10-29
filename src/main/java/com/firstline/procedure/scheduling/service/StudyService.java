package com.firstline.procedure.scheduling.service;


import com.firstline.procedure.scheduling.dto.StudyDto;

public interface StudyService {

    public void createStudy(StudyDto studyDto);
    public void updateStudy(StudyDto studyDto);
    public void deleteStudy(StudyDto studyDto);
}

