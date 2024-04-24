package it.academy.utils.converters;

import java.util.List;

public interface EntityConverter<T, R> {

    T convertToDTO(R entity);

    R convertToEntity(T dto);

    List<T> convertToDTOList(List<R> entityList);
}
