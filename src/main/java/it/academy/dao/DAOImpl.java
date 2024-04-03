package it.academy.dao;

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

        return createFindUniqueResultQuery(findByParameter, root,
                criteriaBuilder().equal(root.get(filter), parameter));
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

        return createFindAllQuery(findAll, root);
    }


    @Override
    public List<T> findForPage(int pageNumber, int listSize) {
        CriteriaQuery<T> findList = criteriaBuilder().createQuery(clazz);
        Root<T> root = findList.from(clazz);

        return createFindForPageQuery(findList, root, pageNumber, listSize);
    }

    @Override
    public List<T> findByAnyMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        return createFindAllQuery(findByParameters, root, anyMatch);
    }

    @Override
    public List<T> findForPageByAnyMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);
        Predicate anyMatch = createFindByAnyMatchPredicate(root, parameters);

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, anyMatch);
    }

    @Override
    public List<T> findByExactMatch(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        return createFindAllQuery(findByParameters, root, exactMatch);
    }

    @Override
    public List<T> findForPageByExactMatch(int pageNumber, int listSize, List<ParameterContainer<?>> parameters) {
        CriteriaQuery<T> findByParameters = criteriaBuilder().createQuery(clazz);
        Root<T> root = findByParameters.from(clazz);
        Predicate exactMatch = createFindByExactMatchQuery(root, parameters);

        return createFindForPageQuery(findByParameters, root, pageNumber, listSize, exactMatch);
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


    protected Predicate createFindByAnyMatchPredicate(Root<T> root, List<ParameterContainer<?>> containers) {

        Predicate predicate = criteriaBuilder().disjunction();
        containers.forEach(c -> predicate.getExpressions()
                .add(criteriaBuilder()
                        .or(criteriaBuilder()
                                .like(root.get(c.getParameterName()).as(String.class),
                                        ParameterManager.getParameterForLikeQuery(c.getParameterValue())
                                )))
        );
        return predicate;
    }

    protected Predicate createFindByExactMatchQuery(Root<T> root, List<ParameterContainer<?>> containers) {

        Predicate predicate = criteriaBuilder().disjunction();
        containers.forEach(c -> predicate.getExpressions()
                .add(criteriaBuilder()
                        .or(criteriaBuilder()
                                .equal(root.get(c.getParameterName()), c.getParameterValue())
                        ))
        );
        return predicate;
    }

    protected T createFindUniqueResultQuery(CriteriaQuery<T> query, Root<T> root, Predicate... predicates) {
        query.select(root)
                .where(predicates)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager().createQuery(query)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    protected List<T> createFindAllQuery(CriteriaQuery<T> query, Root<T> root, Predicate... predicates) {
        query.select(root)
                .where(predicates)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager().createQuery(query)
                .getResultList();
    }

    protected List<T> createFindForPageQuery(CriteriaQuery<T> query, Root<T> root, int pageNumber, int listSize, Predicate... predicates) {
        query.select(root)
                .where(predicates)
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager()
                .createQuery(query)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    protected Class<T> getClazz() {
        return clazz;
    }
}