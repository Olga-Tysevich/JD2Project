package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.Account;
import it.academy.utils.enums.RepairStatus;

import java.util.List;

public interface AccountDAO extends DAO<Account, Long> {

    boolean checkIfEmailExist(long id, String email);

    List<Account> findServiceCenterAccounts(long serviceCenterId);

    List<Account> findAccountsByServiceCenter(String serviceCenterName, int pageNumber, int listSize);

    long getNumberOfEntriesByServiceCenter(String serviceCenter);

}
