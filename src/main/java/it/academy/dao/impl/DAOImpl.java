package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static it.academy.utils.constants.Constants.*;

public abstract class DAOImpl<T, R> implements DAO<T, R> {
    private TransactionManger manger;
    private Class<T> clazz;

    public DAOImpl(TransactionManger manger, Class<T> clazz) {
        this.manger = manger;
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
    public <S> T findByUniqueParameter(String filter, S input) {
        CriteriaQuery<T> findByParameter = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameter.from(clazz);
        findByParameter.select(root)
                .where(criteriaBuilder().equal(root.get(filter), input))
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
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
        String query = String.format(GET_LIST, clazz.getSimpleName());
        return entityManager().createQuery(query, clazz)
                .getResultList();
    }


    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        String query = String.format(GET_LIST, clazz.getSimpleName());
        return entityManager()
                .createQuery(query, clazz)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findForPageByAnyMatch(int pageNumber, int listSize, String filter, String input) {
        String query = String.format(GET_LIST_BY_ANY_MATCH, clazz.getSimpleName(), filter);
        String parameterVal = String.format(LIKE_QUERY_PATTERN, input);
        return entityManager()
                .createQuery(query, clazz)
                .setParameter(PARAMETER_VALUE, parameterVal)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntries() {
        String query = String.format(GET_NUMBER_OF_ENTRIES, clazz.getSimpleName());
        TypedQuery<Long> count = entityManager().createQuery(query, Long.class);
        return count.getSingleResult();
    }


    public long getNumberOfEntriesByFilter(String filter, String value) {
        String query = String.format(GET_NUMBER_OF_ENTRIES_BY_ANY_MATCH, clazz.getSimpleName(), filter);
        TypedQuery<Long> count = entityManager().createQuery(query, Long.class);
        count.setParameter(PARAMETER_VALUE, String.format(LIKE_QUERY_PATTERN, value).trim());
        return count.getSingleResult();
    }


    protected EntityManager entityManager() {
        return manger.entityManager();
    }

    protected CriteriaBuilder criteriaBuilder() {
        return manger.criteriaBuilder();
    }

    protected Predicate createLikePredicate(Root<T> root, String filter, String value) {
        return criteriaBuilder()
                .like(root.get(filter).as(String.class),
                        String.format(LIKE_QUERY_PATTERN, value).trim());
    }
}
