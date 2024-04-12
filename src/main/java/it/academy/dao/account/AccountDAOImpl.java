package it.academy.dao.account;

import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.utils.dao.TransactionManger;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl() {
        super(new TransactionManger(), Account.class);
    }

}
