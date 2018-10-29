package com.firstline.procedure.scheduling.service;


import com.firstline.procedure.scheduling.dto.StudyDto;

public interface StudyService {

    public StudyDto createStudy(StudyDto studyDto);
    public StudyDto updateStudy(StudyDto studyDto);
    public void deleteStudy(Long id);
}

