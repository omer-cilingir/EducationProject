package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.CommentDto;
import com.education.model.entity.CommentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentConverter extends GenericConverter<CommentDto, CommentRequest> {

}