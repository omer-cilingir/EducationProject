package com.education.model.entity;

import com.education.model.entity.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionRequest extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String content;

    @Size(max = 50)
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel sourceUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel targetUser;


}
