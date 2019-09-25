package com.education.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.education.model.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoleModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String name;

}
