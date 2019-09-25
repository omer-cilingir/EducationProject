package com.education.repository;

import com.education.model.entity.QuestionRequest;
import com.education.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRequestRepository extends BaseRepository<QuestionRequest> {

    public List<QuestionRequest> findByCreatedBy(String createdBy);

    public List<QuestionRequest> findByTargetUserUsername(String username);

    public List<QuestionRequest> findBySourceUserId(Long id);

    public List<QuestionRequest> findByTargetUserId(Long id);

    public List<QuestionRequest> findByTargetUserIdIsNull();

}
