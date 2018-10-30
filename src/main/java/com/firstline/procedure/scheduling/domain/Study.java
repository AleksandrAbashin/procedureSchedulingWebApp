package com.firstline.procedure.scheduling.domain;

import com.firstline.procedure.scheduling.domain.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "studies")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Column(name = "description")
    private  String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_status",nullable=false)
    private Status status;

    @Column(name = "planned_start_time",nullable=false)
    private LocalDate plannedStartTime;

    @Column(name = "estimated_end_time")
    private LocalDate estimatedEndTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Study() {

    }

    public void setPlannedStartTime(LocalDate plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public void setEstimatedEndTime(LocalDate estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}