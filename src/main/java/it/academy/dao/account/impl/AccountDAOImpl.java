package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;

public class AccountDAOImpl <T extends Account> extends DAOImpl<T, Long> implements AccountDAO<T> {

    public AccountDAOImpl(Class<T> clazz) {
        super(clazz);
    }

}
