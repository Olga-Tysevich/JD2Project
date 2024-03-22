package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.Account;

public interface AccountDAO<T extends Account> extends DAO<T, Long> {

    Account getByEmail(String email);

    Account getByName(String name);

}
