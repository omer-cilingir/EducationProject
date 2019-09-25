package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.QuestionDto;
import com.education.model.entity.QuestionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionConverter extends GenericConverter<QuestionDto, QuestionRequest> {
}
