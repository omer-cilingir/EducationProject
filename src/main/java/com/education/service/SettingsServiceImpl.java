package com.education.service;

import com.education.model.entity.Settings;
import com.education.repository.SettingsRepository;
import com.education.service.base.CrudServiceImpl;
import com.education.service.interfaces.SettingsService;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl extends CrudServiceImpl<Settings> implements SettingsService {

    private SettingsRepository repository;

    public SettingsServiceImpl(SettingsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Settings findByUserId(long userId) {
        return repository.findByUserId(userId);
    }
}
