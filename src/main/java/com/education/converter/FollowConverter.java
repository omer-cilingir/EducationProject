package com.education.converter;

import org.mapstruct.Mapper;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.FollowDto;
import com.education.model.entity.FollowEntity;

@Mapper(componentModel = "spring")
public interface FollowConverter extends GenericConverter<FollowDto, FollowEntity>{

}
