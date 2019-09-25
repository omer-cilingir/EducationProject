package com.education.web.controller.interfaces;

import com.education.model.dto.DocumentDto;
import com.education.model.entity.DocumentRequest;
import com.education.web.controller.base.CrudController;

import java.util.List;

public interface DocumentRequestController extends CrudController<DocumentDto, DocumentRequest> {

    public List<DocumentDto> getDocumentListByCreatedBy(String createdBy);

    public List<DocumentDto> getFreeDocumentList();

    public List<DocumentDto> getPaidDocumentList();
}
