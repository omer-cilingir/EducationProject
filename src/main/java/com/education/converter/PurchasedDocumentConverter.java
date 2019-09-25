package com.education.converter;

import org.mapstruct.Mapper;

import com.education.converter.base.GenericConverter;
import com.education.model.dto.PurchasedDocumentDto;
import com.education.model.entity.PurchasedDocumentEntity;

@Mapper(componentModel = "spring")
public interface PurchasedDocumentConverter extends GenericConverter<PurchasedDocumentDto, PurchasedDocumentEntity>{

}
