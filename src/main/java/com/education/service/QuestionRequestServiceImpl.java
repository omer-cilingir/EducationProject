package com.education.service;

import com.education.model.entity.UserModel;
import com.education.repository.QuestionRequestRepository;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.QuestionRequestService;
import com.education.service.interfaces.UserService;
import com.education.model.entity.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionRequestServiceImpl extends CrudServiceImpl<QuestionRequest> implements QuestionRequestService {

    private QuestionRequestRepository repository;

    private UserService userService;

    @Autowired
    public QuestionRequestServiceImpl(QuestionRequestRepository repository, UserService userService) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<QuestionRequest> getMyQuestionList() {
        UserModel currentUser = this.userService.getCurrentUser();
        return this.repository.findBySourceUserId(currentUser.getId());
    }

    @Override
    public List<QuestionRequest> getOtherQuestionList() {
        UserModel currentUser = this.userService.getCurrentUser();
        return this.repository.findByTargetUserId(currentUser.getId());
    }

    @Override
    public List<QuestionRequest> getGeneralQuestionList() {
        return this.repository.findByTargetUserIdIsNull();
    }


}
