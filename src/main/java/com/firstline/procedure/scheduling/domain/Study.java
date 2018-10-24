package com.firstline.procedure.scheduling.domain;

import com.firstline.procedure.scheduling.domain.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long studyId;

    @Column(name = "patient",nullable=false)
    @ManyToMany
    Patient patient;

    @Column(name = "study_status",nullable=false)
    Status status;

    @Column(name = "planned_start_time",nullable=false)
    LocalDateTime plannedStartTime;

    @Column(name = "estimated_end_time")
    LocalDateTime estimatedEndTime;

}