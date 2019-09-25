package com.education.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;
}
