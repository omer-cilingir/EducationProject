package com.education.model.entity;

import com.education.model.entity.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "settings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Settings extends BaseModel {

    private String token;
    @OneToOne
    private UserModel user;
}
