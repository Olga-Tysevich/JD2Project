package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.utils.dao.ParameterContainer;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

import static it.academy.utils.Constants.*;

public class AccountDAOImpl <T extends Account> extends DAOImpl<T, Long> implements AccountDAO<T> {

    public AccountDAOImpl(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public <S> T findByUniqueParameter(String filter, S parameter) {
        CriteriaQuery<T> findByParameter = createFindQuery();

        return entityManager().createQuery(findByParameter)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> findAll = createFindQuery();
        return entityManager().createQuery(findAll).getResultList();
    }

    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findList = createFindQuery();
        return entityManager().createQuery(findList)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findByAnyMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createFindQuery(parameters);
        return entityManager().createQuery(findByParameters)
                .getResultList();
    }

    @Override
    public List<T> findForPageByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createFindQuery(parameters);
        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }



    @Override
    public List<T> findByExactMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createFindQuery(parameters);
        Root<T> root = findByParameters.from(getClazz());
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        findByParameters.where(criteriaBuilder().
                and(exactMatch));

        return entityManager().createQuery(findByParameters)
                .getResultList();
    }

    @Override
    public List<T> findForPageByExactMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createFindQuery(parameters);
        Root<T> root = findByParameters.from(getClazz());
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        findByParameters.where(criteriaBuilder().
                and(exactMatch));

        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findBlockedAccounts() {
        CriteriaQuery<T> findBlockedAccount = createFindQuery();
        Root<T> root = findBlockedAccount.from(getClazz());

        findBlockedAccount.where(criteriaBuilder().
                        and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false)));

        return entityManager()
                .createQuery(findBlockedAccount)
                .getResultList();
    }

    @Override
    public List<T> findBlockedAccountsForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findBlockedAccount = createFindQuery();
        Root<T> root = findBlockedAccount.from(getClazz());

        findBlockedAccount
                .where(criteriaBuilder().
                        and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false)));

        return entityManager()
                .createQuery(findBlockedAccount)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findBlockedAccount = createFindQuery();
        Root<T> root = findBlockedAccount.from(getClazz());

        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);
        findBlockedAccount
                .where(anyMatch,
                        criteriaBuilder().
                                and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false)));

        return entityManager()
                .createQuery(findBlockedAccount)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    private CriteriaQuery<T> createFindQuery() {
        CriteriaQuery<T> findAll = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findAll.from(getClazz());
        findAll.select(root)
                .where(root.type().in(getClazz()))
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return findAll;
    }

    private CriteriaQuery<T> createFindQuery(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(getClazz());
        Root<T> root = findByParameters.from(getClazz());

        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);
        findByParameters.select(root)
                .where(root.type().in(getClazz()),
                        anyMatch)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return findByParameters;
    }
}
