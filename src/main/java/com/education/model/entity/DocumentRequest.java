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
@Table(name = "document")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocumentRequest extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String header;
    private String description;
    private String fileUrl;
    private boolean isPaidDocument;
    private int price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel sourceUser; 

}
