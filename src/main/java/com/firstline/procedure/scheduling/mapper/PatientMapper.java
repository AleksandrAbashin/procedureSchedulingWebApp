package com.firstline.procedure.scheduling.mapper;

import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.dto.PatientDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toPatient(PatientDto patientDto);

    PatientDto fromPatient(Patient patient);

    List<PatientDto> fromListPatient(List<Patient> patients);
}
