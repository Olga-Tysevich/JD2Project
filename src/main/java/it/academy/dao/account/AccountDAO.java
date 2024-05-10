package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.Account;
import java.util.List;

public interface AccountDAO extends DAO<Account, Long> {

    boolean checkIfEmailExist(long id, String email);

    List<Account> findServiceCenterAccounts(long serviceCenterId);

}
