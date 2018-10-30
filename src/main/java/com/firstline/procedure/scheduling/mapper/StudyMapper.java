package com.firstline.procedure.scheduling.mapper;

import com.firstline.procedure.scheduling.domain.Study;
import com.firstline.procedure.scheduling.dto.StudyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    @Mapping(source = "patientId", target = "patient.patientId")
    Study toStudy(StudyDto studyDto);

    @Mapping(source = "patient.patientId", target = "patientId")
    StudyDto fromStudy(Study study);

    List<StudyDto> fromListStudy(List<Study> studies);
}
