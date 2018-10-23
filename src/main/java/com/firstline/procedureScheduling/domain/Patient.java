package com.firstline.procedureScheduling.domain;

import com.firstline.procedureScheduling.domain.enums.Sex;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long patientId;

    @Column(name = "patient_name",nullable=false)
    String patientName;

    @Column(name = "patient_sex")
    Sex patientSex;

    @Column(name = "day_of_birth")
    LocalDateTime patientDateBirth;

    @OneToMany
    List<Study> listStudy;

}