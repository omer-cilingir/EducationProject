package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.RoleDto;
import com.education.model.entity.RoleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter extends GenericConverter<RoleDto, RoleModel> {
}
