package com.education.web.advice;

import com.education.exception.EducationException;
import com.education.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@ResponseBody
public final class GenericControllerAdvice {

    @ExceptionHandler(EducationException.class)
    private ErrorResponse handle(EducationException exception,
                                 final HttpServletResponse httpServletResponse) {

        httpServletResponse.setStatus(HttpStatus.OK.value());

        return ErrorResponse.builder()
                .code(exception.getStatusCode())
                .message(exception.getMessage())
                .build();
    }
}
