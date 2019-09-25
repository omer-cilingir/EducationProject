package com.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.model.entity.DocumentRequest;
import com.education.repository.DocumentRequestRepository;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.DocumentRequestService;

@Service
@Transactional(readOnly = true)
public class DocumentRequestServiceImpl extends CrudServiceImpl<DocumentRequest> implements DocumentRequestService {

    DocumentRequestRepository repository;

    @Autowired
    public DocumentRequestServiceImpl(DocumentRequestRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<DocumentRequest> getDocumentListByCreatedBy(String createdBy) {
        return this.repository.findByCreatedBy(createdBy);
    }

	@Override
	public List<DocumentRequest> getDocumentListByIsPaidDocument(boolean isPaidDocument) {
		return this.repository.findByIsPaidDocument(isPaidDocument);
	}

	@Override
	public DocumentRequest getDocument(Long id) {
		return this.repository.getOne(id);
	}

}
