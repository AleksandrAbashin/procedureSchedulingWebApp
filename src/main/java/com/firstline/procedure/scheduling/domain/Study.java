package com.firstline.procedure.scheduling.domain;

import com.firstline.procedure.scheduling.domain.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Column(name = "description")
    private  String description;

    @Column(name = "study_status",nullable=false)
    private Status status;

    @Column(name = "planned_start_time",nullable=false)
    private LocalDateTime plannedStartTime;

    @Column(name = "estimated_end_time")
    private LocalDateTime estimatedEndTime;

    @Column(name = "patient",nullable=false)
    @ManyToMany
    @JoinTable(name = "patient_study", joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "study_id"))
    private List<Patient> patients;

}