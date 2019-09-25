package com.education.repository;

import com.education.model.entity.DocumentRequest;
import com.education.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRequestRepository extends BaseRepository<DocumentRequest> {

    List<DocumentRequest> findByCreatedBy(String createdBy);
    List<DocumentRequest> findByIsPaidDocument(boolean isPaidDocument);
}
