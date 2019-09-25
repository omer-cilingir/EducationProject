package com.education.web.controller;

import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.PreparationExamController;
import com.education.service.interfaces.PreparationExamService;
import com.education.converter.PreparationExamConverter;
import com.education.model.dto.PreparationExamDto;
import com.education.model.entity.PreparationExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/examType")
public class PreparationExamControllerImpl extends CrudControllerImpl<PreparationExamDto, PreparationExam> implements PreparationExamController {

    @Autowired
    public PreparationExamControllerImpl(PreparationExamService service, PreparationExamConverter converter) {
        super(service, converter);
    }

}
