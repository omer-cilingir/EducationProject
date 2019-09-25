package com.education.web.controller.base;

import com.education.service.base.CrudService;
import com.education.converter.base.GenericConverter;
import com.education.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class CrudControllerImpl<D, E> implements CrudController<D, E> {

    private CrudService<E> service;
    private GenericConverter<D, E> converter;
    private Class<E> modelClass;
    private Class<D> dtoClass;
    private String modelClassName;
    private String dtoClassName;

    @SuppressWarnings("unchecked")
    public CrudControllerImpl(CrudService<E> service, GenericConverter<D, E> converter) {
        this.service = service;
        this.converter = converter;
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.dtoClass = (Class<D>) genericSuperclass.getActualTypeArguments()[0];
        this.modelClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.dtoClassName = this.dtoClass.getSimpleName();
        this.modelClassName = this.modelClass.getSimpleName();
    }

    @PostMapping(path = "/add")
    public D create(@RequestBody @Valid D model) {
        log.debug("Creating a new {} data with information: {}", modelClassName, JsonMapper.toJsonString(model));
        E created = service.create(converter.toEntity(model));
        D dto = converter.toDto(created);
        log.debug("Created a new {} data with information: {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(created), dtoClassName, JsonMapper.toJsonString(dto));
        return dto;
    }

    @PutMapping(path = "/update/{id}")
    public D update(@PathVariable("id") Long id, @RequestBody Map<String, Object> updateValues) {
        log.debug("Updating {} {} data with information: {}", modelClassName, id, JsonMapper.toJsonString(updateValues));
        E updated = service.update(id, updateValues);
        D dto = converter.toDto(updated);
        log.debug("Updated {} data with information: {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(updated), dtoClassName, JsonMapper.toJsonString(dto));
        return dto;
    }

    @DeleteMapping(path = "/delete/{id}")
    public D delete(@PathVariable("id") Long id) {
        log.debug("Deleting {} data id : {}", modelClassName, id);
        E deleted = service.delete(id);
        D dto = converter.toDto(deleted);
        log.debug("Deleted a new {} data with information: {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(deleted), dtoClassName, JsonMapper.toJsonString(dto));
        return dto;

    }

    @GetMapping(path = "/search/{id}")
    public D findById(@PathVariable("id") Long id) {
        log.debug("Finding {} data with id : {}", modelClassName, id);
        E model = service.findById(id);
        D dto = converter.toDto(model);
        log.debug("Found {} data {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(model), dtoClassName, JsonMapper.toJsonString(dto));
        return dto;
    }

    @GetMapping(path = "/all")
    public List<D> findAll() {
        log.debug("Finding all {} dataList", modelClassName);
        List<E> dataList = service.findAll();
        List<D> dtos = converter.toListDto(dataList);
        log.debug("Found all {} dataList {} converted to {} data with information {}"
                , modelClassName, JsonMapper.toJsonString(dataList), dtoClassName, JsonMapper.toJsonString(dtos));
        return dtos;
    }

}
