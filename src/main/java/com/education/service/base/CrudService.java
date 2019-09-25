package com.education.service.base;

import java.util.List;
import java.util.Map;

public interface CrudService<E> {

    public E create(E model);

    public E update(Long id, Map<String, Object> updateValues);

    public E delete(Long id);

    public E findById(Long id);

    public List<E> findAll();

}
