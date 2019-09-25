package com.education.service;

import com.education.model.entity.FollowEntity;
import com.education.model.entity.UserModel;
import com.education.model.NotificationMessage;
import com.education.rabbitmq.producer.QueueProducer;
import com.education.repository.FollowRepository;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.FollowService;
import com.education.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl extends CrudServiceImpl<FollowEntity> implements FollowService {


    FollowRepository repository;
    UserService userService;

    @Autowired
    public FollowServiceImpl(FollowRepository repository, UserService userService) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<FollowEntity> getFollowerList() {
        return this.repository.findByTargetUserIdAndIsConfirmation(userService.getCurrentUser().getId(), true);
    }

    @Override
    public List<FollowEntity> getFollowingList() {
        return this.repository.findBySourceUserIdAndIsConfirmation(userService.getCurrentUser().getId(), true);
    }

    @Override
    public FollowEntity getWillConfirmation(Long sourceUserId) {
        return this.repository.findBySourceUserIdAndTargetUserId(sourceUserId, userService.getCurrentUser().getId());
    }

    @Override
    public List<FollowEntity> getFollowRequstList() {
        return this.repository.findByTargetUserIdAndIsConfirmation(userService.getCurrentUser().getId(), false);
    }

    @Override
    public boolean followRequest(FollowEntity followRequest) {
        FollowEntity creatingRecord = this.create(followRequest);
        UserModel user = userService.findUserById(creatingRecord.getSourceUser().getId());
        String message = user.getUsername() + " wants to follow you!";
        String token = user.getUsername() + " wants to follow you!";//TODO
        QueueProducer.sendFollowQueue(NotificationMessage.builder()
                .payload(message) //TODO
                .to(token).build());
        return true;
    }

}
