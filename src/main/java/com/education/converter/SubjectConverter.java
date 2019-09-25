package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.SubjectDto;
import com.education.model.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectConverter extends GenericConverter<SubjectDto, Subject> {

}
