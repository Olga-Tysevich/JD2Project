package it.academy.converters;

import java.util.List;

public interface Converter<T,R> {

    R convertToDTOReq(T object);

    T convertDTOReqToEntity(R req);

    List<R> convertListToDTOReq(List<T> entityList);

    List<T> convertDTOReqListToEntityList(List<R> reqList);

}
