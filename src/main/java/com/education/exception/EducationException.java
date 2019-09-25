package com.education.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationException extends RuntimeException {

    final int statusCode;
    final String message;

    public EducationException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
