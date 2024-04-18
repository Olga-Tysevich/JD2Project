package it.academy.dao;

import it.academy.entities.Account;

import java.util.List;

public interface AccountDAO extends DAO<Account, Long> {

    boolean checkIfEmailExist(Account account);

    List<Account> findServiceCenterAccounts(long serviceCenterId);

    List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber, int listSize);

    List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber,
                                            int listSize, String filter, String value);

}
