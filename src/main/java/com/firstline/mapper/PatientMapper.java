package com.firstline.mapper;

import com.firstline.dto.PatientDto;
import com.firstline.domain.Patient;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toPatient(PatientDto patientDto);

    PatientDto fromPatient(Patient patient);

    List<PatientDto> fromListPatient(List<Patient> patients);
}
