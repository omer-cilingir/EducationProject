package com.education.service.interfaces;

import com.education.model.entity.PurchasedDocumentEntity;
import com.education.service.base.CrudService;

public interface PurchasedDocumentService extends CrudService<PurchasedDocumentEntity>{
	
	public PurchasedDocumentEntity buyDocument(Long documentId);

}
