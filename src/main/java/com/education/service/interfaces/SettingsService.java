package com.education.service.interfaces;

import com.education.model.entity.Settings;
import com.education.service.base.CrudService;

public interface SettingsService extends CrudService<Settings> {

    Settings findByUserId(long userId);
}
