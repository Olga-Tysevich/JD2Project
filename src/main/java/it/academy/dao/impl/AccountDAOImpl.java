package it.academy.dao.impl;

import it.academy.dao.AccountDAO;
import it.academy.entities.Account;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl() {
        super(Account.class);
    }

}
