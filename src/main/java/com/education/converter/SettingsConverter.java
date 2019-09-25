package com.education.converter;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.SettingsDto;
import com.education.model.entity.Settings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingsConverter extends GenericConverter<SettingsDto, Settings> {
}
