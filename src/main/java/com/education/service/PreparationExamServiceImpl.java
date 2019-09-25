package com.education.service;

import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.PreparationExamService;
import com.education.model.entity.PreparationExam;
import com.education.repository.PreparationExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreparationExamServiceImpl extends CrudServiceImpl<PreparationExam> implements PreparationExamService {

    @Autowired
    public PreparationExamServiceImpl(PreparationExamRepository repository) {
        super(repository);
    }

}
