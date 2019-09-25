package com.education.service.interfaces;

import com.education.service.base.CrudService;
import com.education.model.entity.CommentRequest;

import java.util.List;

public interface CommentRequestService extends CrudService<CommentRequest> {

    public List<CommentRequest> findByTargetQuestionIdList(Long id);

}
