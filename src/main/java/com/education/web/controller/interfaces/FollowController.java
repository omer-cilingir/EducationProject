package com.education.web.controller.interfaces;

import java.util.List;

import com.education.model.MessageResponse;
import com.education.model.dto.FollowDto;
import com.education.model.entity.FollowEntity;
import com.education.web.controller.base.CrudController;

public interface FollowController extends CrudController<FollowDto, FollowEntity> {

    public List<FollowDto> getFollowerList();

    public List<FollowDto> getFollowingList();

    public FollowDto confirmation(Long sourceUserId);

    public List<FollowDto> getFollowRequestList();
    
    public MessageResponse followRequest(FollowEntity followEntity);
}
