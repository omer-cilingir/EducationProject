package com.education.converter.base;

import java.util.List;

public interface GenericConverter<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toListEntity(List<D> dtos);

    List<D> toListDto(List<E> entities);


}
