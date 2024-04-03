package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.utils.dao.ParameterContainer;

import javax.persistence.criteria.*;
import java.util.List;

import static it.academy.utils.Constants.*;

public class AccountDAOImpl<T extends Account> extends DAOImpl<T, Long> implements AccountDAO<T> {

    public AccountDAOImpl(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public <S> T findByUniqueParameter(String filter, S parameter) {
        CriteriaQuery<T> findByParameter = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameter.from(getClazz());
        Predicate equals = criteriaBuilder().equal(root.get(filter), parameter);
        Predicate account = createFindAccountPredicate(root);

        return createFindUniqueResultQuery(findByParameter, root,
                account, equals);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> findAll = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findAll.from(getClazz());
        Predicate account = createFindAccountPredicate(root);

        return createFindAllQuery(findAll, root, account);
    }

    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findList = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findList.from(getClazz());

        return createFindForPageQuery(findList, root, pageNumber, listSize);
    }

    @Override
    public List<T> findByAnyMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findAll = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findAll.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        return createFindAllQuery(findAll, root, account, anyMatch);
    }

    @Override
    public List<T> findForPageByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, account, anyMatch);
    }


    @Override
    public List<T> findByExactMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        return createFindAllQuery(findByParameters, root, account, exactMatch);
    }

    @Override
    public List<T> findForPageByExactMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        return createFindForPageQuery(findByParameters, root,pageNumber, listSize, account, exactMatch);
    }

    @Override
    public List<T> findBlockedAccounts() {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false));

        return createFindAllQuery(findByParameters, root, account, blockedAccount);
    }

    @Override
    public List<T> findBlockedAccountsForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false));

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, account, blockedAccount);
    }

    @Override
    public List<T> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());
        Predicate account = createFindAccountPredicate(root);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);
        Predicate blockedAccount = criteriaBuilder().
                and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false));

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, account, blockedAccount, anyMatch);
    }

    protected Predicate createFindAccountPredicate(Root<T> root) {
        Predicate findAccount = criteriaBuilder().conjunction();
        findAccount.getExpressions().add(root.type().in(getClazz()));
        return findAccount;
    }
}
