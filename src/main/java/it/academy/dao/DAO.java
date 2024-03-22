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

    List<T> findAllByParameters(List<ParameterContainer> parameters);

    List<T> findForPage(int pageNumber, int listSize);

    List<T> findForPageByParameters(int pageNumber, int listSize, List<ParameterContainer> parameters);

    BigInteger getNumberOfEntries();

}
