package com.education.web.controller.interfaces;

import com.education.web.controller.base.CrudController;
import com.education.model.dto.CommentDto;
import com.education.model.entity.CommentRequest;

import java.util.List;

public interface CommentRequestConroller extends CrudController<CommentDto, CommentRequest> {

    List<CommentDto> findByTargetQuestionList(Long id);

}
