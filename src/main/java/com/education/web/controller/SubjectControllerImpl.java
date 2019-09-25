package com.education.web.controller;

import com.education.web.controller.base.CrudControllerImpl;
import com.education.service.interfaces.SubjectService;
import com.education.web.controller.interfaces.SubjectController;
import com.education.converter.SubjectConverter;
import com.education.model.dto.SubjectDto;
import com.education.model.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectControllerImpl extends CrudControllerImpl<SubjectDto, Subject> implements SubjectController {

    private final SubjectService service;
    private final SubjectConverter converter;

    @Autowired
    public SubjectControllerImpl(SubjectService service, SubjectConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }

    @Override
    @GetMapping(path = "/withExam/{id}")
    public List<SubjectDto> getSubjectListWithId(@PathVariable("id") Long id) {
        return converter.toListDto(service.getSubjectListWithId(id));
    }
}
