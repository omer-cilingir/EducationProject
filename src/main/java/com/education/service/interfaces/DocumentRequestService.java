package com.education.service.interfaces;

import java.util.List;

import com.education.model.entity.DocumentRequest;
import com.education.service.base.CrudService;

public interface DocumentRequestService extends CrudService<DocumentRequest> {

    public List<DocumentRequest> getDocumentListByCreatedBy(String createdBy);
    public List<DocumentRequest> getDocumentListByIsPaidDocument(boolean isPaidDocument);
    public DocumentRequest getDocument(Long id);
}
