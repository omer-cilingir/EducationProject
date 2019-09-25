package com.education.web.controller;

import com.education.converter.DocumentConverter;
import com.education.model.dto.DocumentDto;
import com.education.model.entity.DocumentRequest;
import com.education.service.interfaces.DocumentRequestService;
import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.DocumentRequestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/document")
public class DocumentControllerImpl extends CrudControllerImpl<DocumentDto, DocumentRequest> implements DocumentRequestController {

    private final DocumentRequestService service;
    private final DocumentConverter converter;

    @Autowired
    public DocumentControllerImpl(DocumentRequestService service, DocumentConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(path = "/{createdBy}")
    @Override
    public List<DocumentDto> getDocumentListByCreatedBy(@PathVariable("createdBy") String createdBy) {
        return this.converter.toListDto(this.service.getDocumentListByCreatedBy(createdBy));
    }

    @GetMapping(path = "/freeDocuments")
    @Override
    public List<DocumentDto> getFreeDocumentList() {
        return this.converter.toListDto(this.service.getDocumentListByIsPaidDocument(false));
    }

    @GetMapping(path = "/paidDocuments")
    @Override
    public List<DocumentDto> getPaidDocumentList() {
        return this.converter.toListDto(this.service.getDocumentListByIsPaidDocument(true));
    }

}
