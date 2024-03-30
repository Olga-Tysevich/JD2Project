package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceAccount;
import it.academy.entities.account.role.Role;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import org.hibernate.criterion.Restrictions;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static it.academy.utils.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl() {
        super(Account.class);
    }

    @Override
    public <S> Account findByUniqueParameter(String filter, S parameter) {
        CriteriaQuery<Account> findByParameter = createFindQuery();
        Root<Account> root = findByParameter.from(Account.class);
        findByParameter.select(root)
                .where(criteriaBuilder().equal(root.get(filter), parameter));

        return entityManager().createQuery(findByParameter)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        CriteriaQuery<Account> findAll = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findAll.from(Account.class);
        Root<ServiceAccount> serviceAccountRoot = findAll.from(ServiceAccount.class);
        findAll.select(root)
                .where(root.type().in(Account.class));
        return entityManager().createQuery(findAll).getResultList();
    }

    @Override
    public List<Account> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<Account> findList = createFindQuery();
        Root<Account> root = findList.from(Account.class);
        findList.select(root)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));

        return entityManager().createQuery(findList)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Account> findByAnyMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findByParameters = createFindQuery();
        Root<Account> root = findByParameters.from(Account.class);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        findByParameters.select(root)
                .where(anyMatch);
        return entityManager().createQuery(findByParameters)
                .getResultList();
    }

    @Override
    public List<Account> findForPageByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findByParameters = createFindQuery();
        Root<Account> root = findByParameters.from(Account.class);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        findByParameters.select(root)
                .where(anyMatch);
        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Account> findByExactMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findByParameters = createFindQuery();
        Root<Account> root = findByParameters.from(Account.class);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        findByParameters.select(root)
                .where(exactMatch);

        return entityManager().createQuery(findByParameters)
                .getResultList();
    }

    @Override
    public List<Account> findForPageByExactMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findByParameters = createFindQuery();
        Root<Account> root = findByParameters.from(Account.class);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        findByParameters.select(root)
                .where(exactMatch);

        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Account> findBlockedAccounts() {
        CriteriaQuery<Account> findBlockedAccount = createFindQuery();
        Root<Account> root = findBlockedAccount.from(Account.class);

        findBlockedAccount
                .where(criteriaBuilder().
                        and(criteriaBuilder().equal(root.get(IS_ACTIVE_ACCOUNT), false)));

        return entityManager()
                .createQuery(findBlockedAccount)
                .getResultList();
    }

    @Override
    public List<Account> findBlockedAccountsForPage(int pageNumber, int listSize) {
        CriteriaQuery<Account> findBlockedAccount = createFindQuery();
        Root<Account> root = findBlockedAccount.from(Account.class);

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
    public List<Account> findBlockedAccountsByParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Account> findBlockedAccount = createFindQuery();
        Root<Account> root = findBlockedAccount.from(Account.class);

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

    private CriteriaQuery<Account> createFindQuery() {
        CriteriaQuery<Account> findAccount = criteriaBuilder().createQuery(Account.class);
        Root<ServiceAccount> serviceAccountRoot = findAccount.from(ServiceAccount.class);


        findAccount
                .where(criteriaBuilder()
                        .and(criteriaBuilder().equal(serviceAccountRoot.get(SERVICE_CENTER), new ServiceCenter())));

        return findAccount;
    }
}
