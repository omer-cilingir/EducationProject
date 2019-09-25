package com.education.model.dto;

import javax.persistence.ManyToOne;

import com.education.model.dto.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto extends BaseDto{

	@ManyToOne
	private UserDto sourceUser;
	
	@ManyToOne
	private UserDto targetUser;
	
	private boolean isConfirmation;
}
