package com.firstline.procedure.scheduling.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PatientDto {

    private Long id;

    private String patientName;

    private String patientSex;

    private LocalDateTime patientDateBirth;

    private List<StudyDto> studies;
}
