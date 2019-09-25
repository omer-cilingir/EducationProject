package com.education.web.controller;

import com.education.converter.FollowConverter;
import com.education.model.MessageResponse;
import com.education.model.dto.FollowDto;
import com.education.model.entity.FollowEntity;
import com.education.service.interfaces.FollowService;
import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.FollowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/follow")
public class FollowControllerImpl extends CrudControllerImpl<FollowDto, FollowEntity> implements FollowController {

    FollowService service;
    FollowConverter converter;

    @Autowired
    public FollowControllerImpl(FollowService service, FollowConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(path = "/follower")
    @Override
    public List<FollowDto> getFollowerList() {
        return this.converter.toListDto(this.service.getFollowerList());
    }

    @GetMapping(path = "/following")
    @Override
    public List<FollowDto> getFollowingList() {
        return this.converter.toListDto(this.service.getFollowingList());
    }

    @GetMapping(path = "/comfirmation/{sourceUserId}")
    @Override
    public FollowDto confirmation(@PathVariable("sourceUserId") Long sourceUserId) {
        FollowEntity followEntity = this.service.getWillConfirmation(sourceUserId);
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("isConfirmation", true);
        return this.update(followEntity.getId(), updateValues);
    }

    @GetMapping(path = "/followRequest")
    @Override
    public List<FollowDto> getFollowRequestList() {
        return this.converter.toListDto(service.getFollowRequstList());
    }

    @PostMapping(path = "/request")
    @Override
    public MessageResponse followRequest(@Valid @RequestBody FollowEntity followEntity) {
        boolean isReturnListener = service.followRequest(followEntity);
        return MessageResponse.builder()
                .message(isReturnListener ? "Request Sent" : "Technical Error Occurred")
                .build();
    }
}
