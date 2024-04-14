package it.academy.dao;

import it.academy.dao.DAO;
import it.academy.entities.account.Account;

import java.util.List;

public interface AccountDAO extends DAO<Account, Long> {

    List<Account> findServiceCenterAccounts(long serviceCenterId);

    List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber, int listSize);

    List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber,
                                            int listSize, String filter, String value);

}
