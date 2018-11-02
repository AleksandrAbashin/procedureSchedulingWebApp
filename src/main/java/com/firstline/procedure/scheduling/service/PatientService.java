package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.dto.StudyDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    PatientDto getPatientById(Long id);

    PatientDto updatePatient(PatientDto patientDto);

    PatientDto deletePatient(Long id);

    List<PatientDto> getAllPatients();

    List<StudyDto> getListStudiesOfPatient(Long id);

    Pair<Long, List<PatientDto>> getLimitLisOfPatient(int page, int size);
}
