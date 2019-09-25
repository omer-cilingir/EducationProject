package com.education.web.controller.interfaces;

import com.education.model.entity.Subject;
import com.education.web.controller.base.CrudController;
import com.education.model.dto.SubjectDto;

import java.util.List;

public interface SubjectController extends CrudController<SubjectDto, Subject> {

    List<SubjectDto> getSubjectListWithId(Long id);
}
