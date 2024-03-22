package it.academy.dao.impl;

import it.academy.dao.AccountDAO;
import it.academy.entities.account.Account;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl(Class<Account> clazz) {
        super(Account.class);
    }

    @Override
    public Account getByEmail(String email) {
        CriteriaQuery<Account> findByEmail = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByEmail.from(Account.class);
        findByEmail.select(root)
                .where(root.get(EMAIL));
        return entityManager().createQuery(findByEmail).getSingleResult();
    }

    @Override
    public Account getByName(String name) {
        CriteriaQuery<Account> findByName = criteriaBuilder().createQuery(Account.class);
        Root<Account> root = findByName.from(Account.class);

        List<ParameterContainer> parameters = ParameterManager.getQueryParameters(name).stream()
                .filter(p -> String.class.equals(p.getParameterType()))
                .collect(Collectors.toList());

        Predicate likeName = criteriaBuilder().disjunction();

        parameters.forEach(p -> {
            //TODO Проверить как отработает
            likeName.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(USER_NAME), name)));
            likeName.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(USER_SURNAME), name)));
        });

        findByName.select(root)
                .where(likeName);
        return entityManager().createQuery(findByName).getSingleResult();
    }

}
