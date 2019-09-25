package com.education.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.education.model.entity.FollowEntity;
import com.education.repository.base.BaseRepository;

@Repository
public interface FollowRepository extends BaseRepository<FollowEntity>{
	
	List<FollowEntity> findBySourceUserIdAndIsConfirmation(Long id, boolean isConfirmation);
	List<FollowEntity> findByTargetUserIdAndIsConfirmation(Long id, boolean isConfirmation);
	FollowEntity findBySourceUserIdAndTargetUserId(Long sourceUserId, Long targetUserId);
	Optional<FollowEntity> findById(Long id);
}
