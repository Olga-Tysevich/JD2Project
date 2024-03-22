package it.academy.dao;

import it.academy.entities.account.Account;

public interface AccountDAO extends DAO<Account, Long> {

    Account getByEmail(String email);

    Account getByName(String name);

}
