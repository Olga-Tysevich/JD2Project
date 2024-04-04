package it.academy.dao;

import it.academy.entities.Account;
import it.academy.utils.dao.ParameterContainer;

import java.util.List;

public interface AccountDAO extends DAO<Account, Long> {

    List<Account> findBlockedAccounts();

    List<Account> findBlockedAccountsForPage(int pageNumber, int listSize);

    List<Account> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters);

}
