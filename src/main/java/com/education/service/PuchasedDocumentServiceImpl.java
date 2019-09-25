package com.education.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.entity.DocumentRequest;
import com.education.model.entity.PurchasedDocumentEntity;
import com.education.model.entity.UserModel;
import com.education.repository.PurchasedDocumentRepository;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.DocumentRequestService;
import com.education.service.interfaces.PurchasedDocumentService;
import com.education.service.interfaces.UserService;

@Service
public class PuchasedDocumentServiceImpl extends CrudServiceImpl<PurchasedDocumentEntity>
		implements PurchasedDocumentService {

	PurchasedDocumentRepository repository;
	DocumentRequestService documentService;
	UserService userService;

	@Autowired
	public PuchasedDocumentServiceImpl(PurchasedDocumentRepository repository, DocumentRequestService documentService,
			UserService userService) {
		super(repository);
		this.documentService = documentService;
		this.userService = userService;
	}

	@Override
	public PurchasedDocumentEntity buyDocument(Long documentId) {
		PurchasedDocumentEntity puchasedDocument = new PurchasedDocumentEntity();
		DocumentRequest document = documentService.getDocument(documentId);
		UserModel user = userService.getCurrentUser();
		if (user.getCredit() >= document.getPrice()) {
			Map<String, Object> updateValues = new HashMap<>();
			updateValues.put("credit", user.getCredit() - document.getPrice());
			UserModel updatedUser = this.userService.updateUser(user.getId(), updateValues);
			puchasedDocument.setSourceDocument(document);
			puchasedDocument.setSourceUser(updatedUser);
			return this.create(puchasedDocument);
		} else
			return null;
	}

}
