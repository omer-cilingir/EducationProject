package com.education.repository;

import com.education.model.entity.Subject;
import com.education.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends BaseRepository<Subject> {

    List<Subject> findByPreparationExamId(Long id);
}
