package it.academy.dao;

import java.math.BigInteger;
import java.util.List;

public interface DAO<T,R> {

    T create(T object);

    T update(T object);

    T find(R id);

    boolean delete(R id);

    List<T> findAll();

    List<T> findForPage(int pageNumber, int listSize);

    BigInteger getNumberOfEntries();

}
