package com.firstline.procedure.scheduling.mapper;

import com.firstline.procedure.scheduling.domain.Study;
import com.firstline.procedure.scheduling.dto.StudyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {
    Study toStudy(StudyDto studyDto);
    StudyDto fromStudy(Study study);

    List<StudyDto> fromListStudy(List<Study> studies);
}
