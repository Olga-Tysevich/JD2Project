package it.academy.dao;

import java.util.List;

public interface DAO<T, R> {

    T create(T object);

    T update(T object);

    T find(R id);

    <S> T findByUniqueParameter(String filter, S input);

    boolean delete(R id);

    List<T> findAll();

    List<T> findForPage(int pageNumber, int listSize);

    List<T> findForPageByAnyMatch(int pageNumber, int listSize, String filter, String input);

    long getNumberOfEntries();

    long getNumberOfEntriesByFilter(String filter, String value);
}
