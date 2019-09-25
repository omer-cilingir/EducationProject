package com.education.service;

import com.education.service.interfaces.SubjectService;
import com.education.model.entity.Subject;
import com.education.repository.SubjectRepository;
import com.education.service.base.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl extends CrudServiceImpl<Subject> implements SubjectService {

    private SubjectRepository repository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Subject> getSubjectListWithId(Long id) {
        return repository.findByPreparationExamId(id);
    }

}
