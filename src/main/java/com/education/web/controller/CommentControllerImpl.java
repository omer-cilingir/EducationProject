package com.education.web.controller;

import com.education.converter.CommentConverter;
import com.education.model.dto.CommentDto;
import com.education.model.entity.CommentRequest;
import com.education.model.entity.Settings;
import com.education.model.NotificationMessage;
import com.education.rabbitmq.producer.QueueProducer;
import com.education.service.interfaces.CommentRequestService;
import com.education.service.interfaces.SettingsService;
import com.education.util.JsonMapper;
import com.education.util.SecurityUtils;
import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.CommentRequestConroller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
@Slf4j
public class CommentControllerImpl extends CrudControllerImpl<CommentDto, CommentRequest> implements CommentRequestConroller {

    private final CommentRequestService commentRequestService;
    private final SettingsService settingsService;
    private final CommentConverter commentConverter;

    public CommentControllerImpl(CommentRequestService service, SettingsService settingsService, CommentConverter converter) {
        super(service, converter);
        this.commentRequestService = service;
        this.settingsService = settingsService;
        this.commentConverter = converter;
    }

    @GetMapping("/withQuestion/{id}")
    public List<CommentDto> findByTargetQuestionList(@PathVariable("id") Long id) {
        return commentConverter.toListDto(commentRequestService.findByTargetQuestionIdList(id));
    }


    @Override
    public CommentDto create(@RequestBody @Valid CommentDto model) {

        String modelClassName = CommentRequest.class.getSimpleName();
        String dtoClassName = CommentDto.class.getSimpleName();

        log.debug("Creating a new {} data with information: {}", modelClassName, JsonMapper.toJsonString(model));
        CommentRequest created = commentRequestService.create(commentConverter.toEntity(model));
        if (created != null) {
            final Settings settings = settingsService.findByUserId(model.getSourceUser().getId());
            if (settings != null) {
                QueueProducer.sendFollowQueue(NotificationMessage.builder()
                        .title(SecurityUtils.getCurrentUsername() + " size yorum yaptı")
                        .payload(model.getContent())
                        .to(settings.getToken()).build());
            }
        }
        CommentDto dto = commentConverter.toDto(created);
        log.debug("Created a new {} data with information: {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(created), dtoClassName, JsonMapper.toJsonString(dto));
        return dto;
    }

}
