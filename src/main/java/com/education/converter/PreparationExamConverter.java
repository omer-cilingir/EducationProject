package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.PreparationExamDto;
import com.education.model.entity.PreparationExam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreparationExamConverter extends GenericConverter<PreparationExamDto, PreparationExam> {
}
