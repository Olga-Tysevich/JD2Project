package it.academy.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T, R> {

    T create(T object);

    T update(T object);

    T find(R id);

    <S> T findByUniqueParameter(String filter, S input);

    boolean delete(R id);

    List<T> findAll();

    List<T> findAllByPageAndFilter(Integer page, Integer listSize, Map<String, String> searchParam);

    long getNumberOfEntries(Map<String, String> searchParam);
}
