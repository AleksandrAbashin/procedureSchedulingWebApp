package com.firstline.procedure.scheduling.dto;

import com.firstline.procedure.scheduling.domain.Patient;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudyDto {

    private Long id;

    private  String description;

    private String status;

    private LocalDateTime plannedStartTime;

    private LocalDateTime estimatedEndTime;

    private List<Patient> patients;
}
