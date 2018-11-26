package com.firstline.service;


import com.firstline.dto.StudyDto;

public interface StudyService {

    public StudyDto createStudy(StudyDto studyDto);
    public StudyDto updateStudy(StudyDto studyDto);
    public StudyDto getStudyById(Long id);
    public void deleteStudy(Long id);
}

