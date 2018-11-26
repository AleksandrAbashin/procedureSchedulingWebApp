package com.firstline.domain;

import com.firstline.domain.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min=2, max=10)
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
    private List<Study> studies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patientinfo_id")
    private PatientInfo patientInfo;

    public Patient() {
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public Patient(String misha, Sex male, long id) {
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

    public Sex getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Sex patientSex) {
        this.patientSex = patientSex;
    }

    public LocalDate getPatientDateBirth() {
        return patientDateBirth;
    }

    public void setPatientDateBirth(LocalDate patientDateBirth) {
        this.patientDateBirth = patientDateBirth;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }
}