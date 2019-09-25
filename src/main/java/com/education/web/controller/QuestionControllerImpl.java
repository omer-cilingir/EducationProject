package com.education.web.controller;

import com.education.web.controller.base.CrudControllerImpl;
import com.education.service.interfaces.QuestionRequestService;
import com.education.web.controller.interfaces.QuestionRequestController;
import com.education.converter.QuestionConverter;
import com.education.model.dto.QuestionDto;
import com.education.model.entity.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/question")
public class QuestionControllerImpl extends CrudControllerImpl<QuestionDto, QuestionRequest> implements QuestionRequestController {

    private final QuestionRequestService service;
    private final QuestionConverter converter;

    @Autowired
    public QuestionControllerImpl(QuestionRequestService service, QuestionConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(path = "/myQuestions")
    public List<QuestionDto> getMyQuestionList() {
        return converter.toListDto(service.getMyQuestionList());
    }

    @GetMapping(path = "/otherQuestions")
    public List<QuestionDto> getOtherQuestionList() {
        return converter.toListDto(service.getOtherQuestionList());
    }

    @GetMapping(path = "/generalQuestions")
    public List<QuestionDto> getGeneralQuestionList() {
        return converter.toListDto(service.getGeneralQuestionList());
    }
}
