package it.academy.dao.impl;

import it.academy.dao.AccountDAO;
import it.academy.entities.Account;
import it.academy.utils.dao.ParameterContainer;

import javax.persistence.criteria.*;
import java.util.List;

import static it.academy.utils.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl() {
        super(Account.class);
    }

    @Override
    public List<Account> findBlockedAccounts() {
        CriteriaQuery<Account> findByParameters = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByParameters.from(Account.class);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE), false));

        return createFindAllQuery(findByParameters, root, blockedAccount);
    }

    @Override
    public List<Account> findBlockedAccountsForPage(int pageNumber, int listSize) {
        CriteriaQuery<Account> findByParameters = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByParameters.from(Account.class);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE), false));

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, blockedAccount);
    }

    @Override
    public List<Account> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findByParameters = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByParameters.from(Account.class);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE), false));

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, blockedAccount, anyMatch);
    }

}
