package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.entity.UserModel;
import com.education.model.UserRegisterRequest;
import com.education.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter extends GenericConverter<UserDto, UserModel> {

    UserModel requestToEntity(UserRegisterRequest request);
}
