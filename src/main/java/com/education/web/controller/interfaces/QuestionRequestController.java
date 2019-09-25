package com.education.web.controller.interfaces;

import com.education.web.controller.base.CrudController;
import com.education.model.dto.QuestionDto;
import com.education.model.entity.QuestionRequest;

import java.util.List;

public interface QuestionRequestController extends CrudController<QuestionDto, QuestionRequest> {

     List<QuestionDto> getMyQuestionList();

     List<QuestionDto> getOtherQuestionList();

     List<QuestionDto> getGeneralQuestionList();

}
