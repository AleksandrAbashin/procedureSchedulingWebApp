package com.firstline.procedure.scheduling.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


public class PatientDto {

    private Long id;

    private String patientName;

    private String patientSex;

    @DateTimeFormat(iso = DATE)
    private LocalDate patientDateBirth;

    public LocalDate getPatientDateBirth() {
        return patientDateBirth;
    }

    public void setPatientDateBirth(LocalDate patientDateBirth) {
        this.patientDateBirth = patientDateBirth;
    }

    private List<StudyDto> studies;

    public PatientDto() {
           }

    public List<StudyDto> getStudies() {
        return studies;
    }

    public void setStudies(List<StudyDto> studies) {
        this.studies = studies;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }


}
