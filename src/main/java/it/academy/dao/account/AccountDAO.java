package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.Account;
import it.academy.utils.dao.ParameterContainer;

import java.util.List;

public interface AccountDAO<T extends Account> extends DAO<T, Long> {

    List<T> findBlockedAccounts();

    List<T> findBlockedAccountsForPage(int pageNumber, int listSize);

    List<T> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters);

}
