package com.education.service.interfaces;

import com.education.service.base.CrudService;
import com.education.model.entity.QuestionRequest;

import java.util.List;

public interface QuestionRequestService extends CrudService<QuestionRequest> {

    public List<QuestionRequest> getMyQuestionList();

    public List<QuestionRequest> getOtherQuestionList();

    public List<QuestionRequest> getGeneralQuestionList();

}
