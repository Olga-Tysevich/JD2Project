package it.academy.dao;

import it.academy.dao.DAO;

public interface ComponentDAO<T, R> extends DAO<T, R> {

    boolean checkIfComponentExist(R id, String name);

}
