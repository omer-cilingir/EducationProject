package com.education.repository;

import com.education.model.entity.Settings;
import com.education.repository.base.BaseRepository;

public interface SettingsRepository extends BaseRepository<Settings> {

    Settings findByUserId(long userId);
}
