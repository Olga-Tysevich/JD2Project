package it.academy.dao;

import it.academy.utils.dao.ParameterContainer;

import java.math.BigInteger;
import java.util.List;

public interface DAO<T, R> {

    T create(T object);

    T update(T object);

    T find(R id);

    <S> T findByUniqueParameter(String filter, S parameter);

    boolean delete(R id);

    List<T> findAll();

    List<T> findForPage(int pageNumber, int listSize);

    List<T> findByAnyMatch(List<ParameterContainer<?>> parameters);

    List<T> findForPageByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters);

    List<T> findByExactMatch(List<ParameterContainer<?>> parameters);

    List<T> findForPageByExactMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters);

    BigInteger getNumberOfEntries();

}
