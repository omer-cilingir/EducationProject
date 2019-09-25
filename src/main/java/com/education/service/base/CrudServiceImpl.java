package com.education.service.base;

import com.education.model.entity.base.BaseModel;
import com.education.repository.base.BaseRepository;
import com.education.util.DateUtil;
import com.education.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public abstract class CrudServiceImpl<E extends BaseModel> implements CrudService<E> {

    private final BaseRepository<E> repository;
    private final ModelMapper MAPPER = new ModelMapper();
    private Class<E> modelClass;
    private String modelClassName;

    @SuppressWarnings("unchecked")
    public CrudServiceImpl(BaseRepository<E> repository) {
        this.repository = repository;
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.modelClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
        this.modelClassName = this.modelClass.getSimpleName();
    }

    public E create(E model) {
        log.debug("Creating a new {} data with information: {}", modelClassName, model);

        String username = SecurityUtils.getCurrentUsername();
        model.setCreatedBy(username);
        model.setCreatedDate(DateUtil.now());
        model.setLastModifiedBy(username);
        model.setModifiedDate(DateUtil.now());
        E created = repository.save(model);
        log.debug("Created a new {} data with information: {}", modelClassName, model);

        return created;
    }

    public E update(Long id, Map<String, Object> updateValues) {
        log.debug("Updating {} {} data with information: {}", modelClassName, id, updateValues);

        String username = SecurityUtils.getCurrentUsername();
        E updated = findEntityById(id);
        MAPPER.map(updateValues, updated);
        updated.setModifiedDate(DateUtil.now());
        updated.setLastModifiedBy(username);
        updated = repository.save(updated);
        log.debug("Updated {} data with information: {}", modelClassName, updated);

        return updated;
    }

    public E delete(Long id) {
        log.debug("Deleting {} data id : {}", modelClassName, id);

        E deleted = findEntityById(id);
        repository.delete(deleted);
        log.debug("Deleted {} data with information: {}", modelClassName, deleted);

        return deleted;
    }

    public E findById(Long id) {
        log.debug("Finding {} data with id: {}", modelClassName, id);

        E found = findEntityById(id);
        log.debug("Finding {} data wiht information: {}", modelClassName, found);

        return found;
    }

    public List<E> findAll() {
        log.debug("Finding all {} datas", modelClassName);

        List<E> dataList = repository.findAll();
        log.debug("Found all {} datas : {}", modelClassName, dataList);

        return dataList;
    }

    protected E findEntityById(Long id) {
        repository.flush();
        E entity = repository.getOne(id);
        if (entity == null)
            log.debug(String.format("No %s data found with %s: [%s]", modelClassName, "id", id.toString()));
        return entity;
    }
}
