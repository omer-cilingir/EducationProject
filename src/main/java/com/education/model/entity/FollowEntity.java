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
@Table(name = "follow")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FollowEntity extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserModel sourceUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserModel targetUser;
	
	private boolean isConfirmation = false;
}
