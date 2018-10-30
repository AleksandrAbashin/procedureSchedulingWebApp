package com.firstline.procedure.scheduling.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firstline.procedure.scheduling.domain.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "patient_name",nullable=false)
    private String patientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_sex")
    private Sex patientSex;

    @Column(name = "day_of_birth")
    private LocalDate patientDateBirth;

    @OneToMany(mappedBy = "patient",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonInclude
    private List<Study> studies;

    public Patient() {
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Sex getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Sex patientSex) {
        this.patientSex = patientSex;
    }

    public void setPatientDateBirth(LocalDate patientDateBirth) {
        this.patientDateBirth = patientDateBirth;
    }


}