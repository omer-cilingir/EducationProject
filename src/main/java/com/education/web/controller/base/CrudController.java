package com.education.web.controller.base;

import java.util.List;
import java.util.Map;

public interface CrudController<D, E> {

    D create(D model);

    D update(Long id, Map<String, Object> updateValues);

    D delete(Long id);

    D findById(Long id);

    List<D> findAll();
}
