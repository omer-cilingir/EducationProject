package com.education.service.interfaces;

import com.education.service.base.CrudService;
import com.education.model.entity.Subject;

import java.util.List;

public interface SubjectService extends CrudService<Subject> {

    List<Subject> getSubjectListWithId(Long id);

}
