package com.education.web.controller;

import com.education.converter.SettingsConverter;
import com.education.model.dto.SettingsDto;
import com.education.model.entity.Settings;
import com.education.service.interfaces.SettingsService;
import com.education.web.controller.base.CrudControllerImpl;
import com.education.web.controller.interfaces.SettingsController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/settings")
public class SettingsControllerImpl extends CrudControllerImpl<SettingsDto, Settings> implements SettingsController {

    private final SettingsService service;
    private final SettingsConverter converter;

    public SettingsControllerImpl(SettingsService service, SettingsConverter converter) {
        super(service, converter);
        this.service = service;
        this.converter = converter;
    }
}
