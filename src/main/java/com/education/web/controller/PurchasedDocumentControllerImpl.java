package com.education.web.controller;

import com.education.converter.PurchasedDocumentConverter;
import com.education.model.dto.PurchasedDocumentDto;
import com.education.model.entity.PurchasedDocumentEntity;
import com.education.service.interfaces.PurchasedDocumentService;
import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.PuchasedDocumentController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/purchasedDocument")
public class PurchasedDocumentControllerImpl extends CrudControllerImpl<PurchasedDocumentDto, PurchasedDocumentEntity> implements PuchasedDocumentController {

    PurchasedDocumentService service;
    PurchasedDocumentConverter converter;

    public PurchasedDocumentControllerImpl(PurchasedDocumentService service, PurchasedDocumentConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(path = "/buyDocument/{id}")
    @Override
    public PurchasedDocumentDto buyDocument(@PathVariable("id") Long documentId) {
        return converter.toDto(this.service.buyDocument(documentId));
    }

}
