package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import static it.academy.utils.Constants.*;

public abstract class DAOImpl<T, R> implements DAO<T, R> {
    private TransactionManger manger = TransactionManger.getInstance();
    private Class<T> clazz;

    public DAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T object) {
        entityManager().persist(object);
        return object;
    }

    @Override
    public T update(T object) {
        entityManager().merge(object);
        return object;
    }

    @Override
    public T find(R id) {
        return entityManager().find(clazz, id);
    }

    @Override
    public <S> T findByUniqueParameter(String filter, S parameter) {
        CriteriaQuery<T> findByParameter = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameter.from(clazz);
        findByParameter.select(root)
                .where(criteriaBuilder().equal(root.get(filter), parameter));
        return entityManager().createQuery(findByParameter)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(R id) {
        T object = entityManager().find(clazz, id);
        if (object != null) {
            entityManager().remove(object);
        }
        return entityManager().find(clazz, id) == null;
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> findAll = criteriaBuilder().createQuery(clazz);
        Root<T> root = findAll.from(clazz);
        findAll.select(root);
        return entityManager().createQuery(findAll).getResultList();
    }

    @Override
    public List<T> findByAnyMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createQuery(parameters, false);
        return entityManager().createQuery(findByParameters).getResultList();
    }

    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findList = criteriaBuilder().createQuery(clazz);
        Root<T> root = findList.from(clazz);
        findList.select(root)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));

        return entityManager().createQuery(findList)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createQuery(parameters, false);
        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findByAllParameters(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createQuery(parameters, true);
        return entityManager().createQuery(findByParameters).getResultList();
    }

    @Override
    public List<T> findByAllParameters(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = createQuery(parameters, true);
        return entityManager().createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public BigInteger getNumberOfEntries() {
        CriteriaQuery<Long> getTableSize = criteriaBuilder().createQuery(Long.class);
        getTableSize.select(criteriaBuilder().count(getTableSize.from(clazz)));
        long result = entityManager().createQuery(getTableSize).getSingleResult();
        return new BigInteger(String.valueOf(result));
    }

    protected EntityManager entityManager() {
        return manger.entityManager();
    }

    protected CriteriaBuilder criteriaBuilder() {
        return manger.criteriaBuilder();
    }

    private CriteriaQuery<T> createQuery(List<ParameterContainer<?>> parameters, boolean isAndQuery) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);

        Predicate predicate = criteriaBuilder().disjunction();
        parameters.forEach(p -> {
            if (isAndQuery) {
                createAndQuery(predicate, root, p);
            } else {
                createOrQuery(predicate, root, p);
            }
        });

        findByParameters.select(root)
                .where(predicate);
        return findByParameters;
    }

    private void createAndQuery(Predicate predicate, Root<T> root, ParameterContainer<?> parameter) {
        if (parameter.getIsEqualsQuery()) {
            predicate.getExpressions()
                    .add(criteriaBuilder()
                            .and(getEqualsCondition(root, parameter)));
        } else {
            predicate.getExpressions()
                    .add(criteriaBuilder()
                            .and(getLikeCondition(root, parameter)));
        }
    }

    private void createOrQuery(Predicate predicate, Root<T> root, ParameterContainer<?> container) {
        if (container.getIsEqualsQuery()) {
            predicate.getExpressions()
                    .add(criteriaBuilder()
                            .or(getEqualsCondition(root, container)));
        } else {
            predicate.getExpressions()
                    .add(criteriaBuilder()
                            .or(getLikeCondition(root, container)));
        }
    }

    private Predicate getLikeCondition(Root<T> root, ParameterContainer<?> container) {
        return criteriaBuilder()
                .like(root.get(container.getParameterName()).as(String.class),
                        ParameterManager.getParameterForLikeQuery(container.getParameterValue())
                );
    }

    private Predicate getEqualsCondition(Root<T> root, ParameterContainer<?> container) {
        return criteriaBuilder()
                .equal(root.get(container.getParameterName()), container.getParameterValue());
    }
}
