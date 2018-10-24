package com.firstline.procedure.scheduling.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long doctorId;

    @Column(name = "doctor_name")
    String doctorName;

}
