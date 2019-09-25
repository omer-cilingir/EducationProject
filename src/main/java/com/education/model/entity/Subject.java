package com.education.model.entity;

import com.education.model.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "subject")
public class Subject extends BaseModel {
    //ders (okul/lise veya üniversitede belirli bir bilim dalına ait)	: subject

    private static final long serialVersionUID = 1L;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private PreparationExam preparationExam;

}
