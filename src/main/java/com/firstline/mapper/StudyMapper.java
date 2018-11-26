package com.firstline.mapper;

import com.firstline.dto.StudyDto;
import com.firstline.domain.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    @Mapping(source = "patientId", target = "patient.id")
    Study toStudy(StudyDto studyDto);

    @Mapping(source = "patient.id", target = "patientId")
    StudyDto fromStudy(Study study);

    List<StudyDto> fromListStudy(List<Study> studies);
}
