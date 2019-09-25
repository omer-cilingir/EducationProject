package com.education.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.entity.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "puchasedDocument")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchasedDocumentEntity extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private DocumentRequest sourceDocument;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserModel sourceUser;

}
