package com.firstline.procedure.scheduling.service;


import com.firstline.procedure.scheduling.domain.Study;

public interface StudyService {

    public void createStudy(Study study);
    public void updateStudy(Study study);
    public void deleteStudy(Study study);
}

