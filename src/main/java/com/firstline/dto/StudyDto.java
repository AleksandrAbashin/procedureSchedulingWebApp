package com.firstline.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;


public class StudyDto {

  //  @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private  String description;

    private String status;

    @DateTimeFormat(iso = DATE)
    private LocalDate plannedStartTime;

    @DateTimeFormat(iso = DATE)
    private LocalDate estimatedEndTime;

    private Long patientId;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public StudyDto() {

    }

    public StudyDto(Long id, String description, String status, Long patientId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.patientId = patientId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
