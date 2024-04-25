package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.TypedQuery;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl(TransactionManger manger) {
        super(manger, Account.class);
    }

    @Override
    public boolean checkIfEmailExist(long id, String email) {
        TypedQuery<Long> find = entityManager().createQuery(CHECK_ACCOUNT, Long.class);
        find.setParameter(OBJECT_ID, id);
        find.setParameter(EMAIL, email);
        return find.getSingleResult() != 0;
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_ID, Account.class);
        find.setParameter(OBJECT_ID, serviceCenterId);

        return find.getResultList();
    }

    @Override
    public List<Account> findAccountsByServiceCenter(String serviceCenterName, int pageNumber, int listSize) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_NAME, Account.class);
        find.setParameter(PARAMETER_VALUE, String.format(LIKE_QUERY_PATTERN, serviceCenterName));

        return find
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByServiceCenter(String serviceCenter) {
        TypedQuery<Long> count = entityManager().createQuery(GET_NUMBER_OF_ACCOUNTS_BY_SERVICE_CENTER, Long.class);
        count.setParameter(PARAMETER_VALUE, serviceCenter);
        return count.getSingleResult();
    }


}
