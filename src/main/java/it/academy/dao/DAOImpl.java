package it.academy.dao;

import it.academy.entities.device.components.Brand;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.IS_ACTIVE;

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
                .where(criteriaBuilder().equal(root.get(filter), parameter))
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
        CriteriaQuery<T> findAll = criteriaBuilder().createQuery(clazz);
        Root<T> root = findAll.from(clazz);

        findAll.select(root)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));

        return entityManager().createQuery(findAll)
                .getResultList();
    }


    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findList = criteriaBuilder().createQuery(clazz);
        Root<T> root = findList.from(clazz);

        findList.select(root)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager()
                .createQuery(findList)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findForPageByAnyMatch(int pageNumber, int listSize, String filter, String value) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);

        if (filter == null) {
            return findForPage(pageNumber, listSize);
        }

        Predicate predicate = createLikePredicate(root, filter, value);

        findByParameters.select(root)
                .where(predicate)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));

        return entityManager()
                .createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }


    @Override
    public List<T> findActiveObjects(boolean isActive) {
        String query = String.format(FIND_BY_ACTIVE_FIELD, clazz.getSimpleName());
        TypedQuery<T> find = entityManager().createQuery(query, clazz);
        find.setParameter(IS_ACTIVE_PARAMETER, 1);

        return find.getResultList();
    }

    @Override
    public List<T> findActiveObjectsForPage(boolean isActive, int pageNumber, int listSize) {
        String query = String.format(FIND_BY_ACTIVE_FIELD, clazz.getSimpleName());
        TypedQuery<T> find = entityManager().createQuery(query, clazz);
        find.setParameter(IS_ACTIVE_PARAMETER, isActive);

        return find.setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<T> findActiveObjectsForPage(boolean isActive, int pageNumber, int listSize, String filter, String value) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);

        if (filter == null) {
            return findActiveObjectsForPage(isActive, pageNumber, listSize);
        }
        Predicate predicate = createLikePredicate(root, filter, value);

        findByParameters.select(root)
                .where(predicate, criteriaBuilder().equal(root.get(IS_ACTIVE), isActive))
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager()
                .createQuery(findByParameters)
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

    protected Predicate createLikePredicate(Root<T> root, String filter, String value) {
        return criteriaBuilder()
                .like(root.get(filter).as(String.class),
                        ParameterManager.getParameterForLikeQuery(value));
    }

}
