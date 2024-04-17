package it.academy.dao.impl;

import it.academy.dao.AccountDAO;
import it.academy.entities.Account;
import it.academy.entities.ServiceCenter;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl(TransactionManger manger) {
        super(manger, Account.class);
    }

    @Override
    public boolean checkIfExist(Account account) {
        TypedQuery<Account> find = entityManager().createQuery(CHECK_ACCOUNT, Account.class);
        find.setParameter(OBJECT_ID, account.getId());
        find.setParameter(EMAIL, account.getEmail());
        return find.getSingleResult() == null;
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_ID, Account.class);
        find.setParameter(OBJECT_ID, serviceCenterId);

        return find.getResultList();
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber, int listSize) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_ID, Account.class);
        find.setParameter(OBJECT_ID, serviceCenterId);

        return find
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId, int pageNumber, int listSize, String filter, String value) {
        CriteriaQuery<Account> findByParameters = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByParameters.from(Account.class);

        Join<Account, ServiceCenter> join = root.join(ACCOUNT_SERVICE_CENTER);
        findByParameters.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), serviceCenterId));
        Predicate predicate = createLikePredicate(root, filter, value);

        findByParameters.select(root)
                .where(predicate, criteriaBuilder().equal(join, serviceCenterId))
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager()
                .createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

}
