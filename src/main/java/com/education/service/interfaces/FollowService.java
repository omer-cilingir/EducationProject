package com.education.service.interfaces;

import java.util.List;

import com.education.model.entity.FollowEntity;
import com.education.service.base.CrudService;

public interface FollowService extends CrudService<FollowEntity>{

	public List<FollowEntity> getFollowerList();
	public List<FollowEntity> getFollowingList();
	public FollowEntity getWillConfirmation(Long source);
	public List<FollowEntity> getFollowRequstList();
	public boolean followRequest(FollowEntity followRequest);
}
