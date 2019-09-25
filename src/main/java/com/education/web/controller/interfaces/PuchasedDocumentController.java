package com.education.web.controller.interfaces;

import com.education.model.dto.PurchasedDocumentDto;
import com.education.model.entity.PurchasedDocumentEntity;
import com.education.web.controller.base.CrudController;

public interface PuchasedDocumentController extends CrudController<PurchasedDocumentDto, PurchasedDocumentEntity> {

    PurchasedDocumentDto buyDocument(Long documentId);

}
