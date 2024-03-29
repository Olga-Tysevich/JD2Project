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

    <S> List<T> findAllByParameters(boolean isEqualsQuery, List<ParameterContainer<S>> parameters);

    List<T> findForPage(int pageNumber, int listSize);

    <S> List<T> findForPageByParameters(boolean isEqualsQuery, int pageNumber, int listSize, List<ParameterContainer<S>> parameters);

    BigInteger getNumberOfEntries();

}
