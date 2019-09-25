package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.DocumentDto;
import com.education.model.entity.DocumentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentConverter extends GenericConverter<DocumentDto, DocumentRequest> {
}
