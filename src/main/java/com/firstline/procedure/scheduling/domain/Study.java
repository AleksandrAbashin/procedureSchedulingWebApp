package com.firstline.procedure.scheduling.domain;

import com.firstline.procedure.scheduling.domain.enums.Status;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "studies")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description")
    private  String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_status",nullable=false)
    private Status status;

    @NotNull
    @Column(name = "planned_start_time",nullable=false)
    private LocalDate plannedStartTime;

    @Column(name = "estimated_end_time")
    private LocalDate estimatedEndTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Study() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(LocalDate plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public LocalDate getEstimatedEndTime() {
        return estimatedEndTime;
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
}