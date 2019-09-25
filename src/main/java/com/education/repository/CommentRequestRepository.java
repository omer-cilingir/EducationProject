package com.education.repository;

import com.education.model.entity.CommentRequest;
import com.education.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRequestRepository extends BaseRepository<CommentRequest> {

    public List<CommentRequest> findByTargetQuestionId(Long id);

}
