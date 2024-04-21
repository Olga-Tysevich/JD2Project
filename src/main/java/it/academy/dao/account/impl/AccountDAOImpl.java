package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl(TransactionManger manger) {
        super(manger, Account.class);
    }

    @Override
    public boolean checkIfEmailExist(Account account) {
        TypedQuery<Account> find = entityManager().createQuery(CHECK_ACCOUNT, Account.class);
        find.setParameter(OBJECT_ID, account.getId());
        find.setParameter(EMAIL, account.getEmail());
        try {
            Account result = find.getSingleResult();
            return result == null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_ID, Account.class);
        find.setParameter(OBJECT_ID, serviceCenterId);

        return find.getResultList();
    }

}
