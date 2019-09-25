package com.education.service;

import com.education.model.entity.CommentRequest;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.CommentRequestService;
import com.education.repository.CommentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
public class CommentRequestServiceImpl extends CrudServiceImpl<CommentRequest> implements CommentRequestService {

    private CommentRequestRepository repository;

    @Autowired
    public CommentRequestServiceImpl(CommentRequestRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<CommentRequest> findByTargetQuestionIdList(Long id) {
        return this.repository.findByTargetQuestionId(id);
    }

}
