package com.firstline.procedure.scheduling.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


public class PatientDto {

    private Long id;

    @Size(min = 2, max = 30)
    private String patientName;

    private String patientSex;

    @DateTimeFormat(iso = DATE)
    private LocalDate patientDateBirth;



    public PatientDto(@Size(min = 2, max = 30) String patientName, String patientSex) {
        this.patientName = patientName;
        this.patientSex = patientSex;
    }

    public PatientDto(Long id, @Size(min = 2, max = 30) String patientName, String patientSex, List<StudyDto> studies) {
        this.id = id;
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.studies = studies;
    }


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
