package it.academy.dao;


public interface ComponentDAO<T, R> extends DAO<T, R> {

    boolean checkIfExist(R id, String name);

}
