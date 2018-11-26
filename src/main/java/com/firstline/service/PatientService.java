package com.firstline.service;

import com.firstline.domain.PatientInfo;
import com.firstline.dto.PatientDto;
import com.firstline.dto.StudyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    Long createPatient(PatientDto patientDto);

    PatientDto getPatientById(Long id);

    PatientDto updatePatient(PatientDto patientDto);

    PatientDto deletePatient(Long id);

    List<PatientDto> getAllPatients();

    List<StudyDto> getListStudiesOfPatient(Long id);

    Page<PatientDto> paginatedList(Pageable pageable);

    Page<PatientDto> pagination(Pageable pageable);

    PatientInfo getPatientInfoByPatientId(Long id);
}
