package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.entities.repair.Repair_;
import it.academy.utils.TransactionManger;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

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

    @Override
    public List<T> findAllByPageAndFilter(Integer page, Integer listSize, Map<String, String> searchParam) {
        CriteriaQuery<T> find = criteriaBuilder().createQuery(clazz);
        Root<T> root = find.from(clazz);
        Predicate[] predicates = createPredicate(root, searchParam);

        if (predicates.length != 0) {
            find.select(root).where(criteriaBuilder().and(predicates));
        }
        find.orderBy(criteriaBuilder().desc(root.get(Repair_.ID)));

        if (page == null || listSize == null) {
            return entityManager().createQuery(find).getResultList();
        }

        return entityManager().createQuery(find)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntries(Map<String, String> searchParam) {
        CriteriaQuery<Long> find = criteriaBuilder().createQuery(Long.class);
        Root<T> root = find.from(clazz);
        Predicate[] predicates = createPredicate(root, searchParam);
        find.select(criteriaBuilder().count(root));
        if (predicates.length != 0) {
            find.where(criteriaBuilder().and(predicates));
        }
        return entityManager().createQuery(find)
                .getSingleResult();
    }

    protected Predicate[] createPredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                Class<?> fClass = root.get(filter).getJavaType();
                addSimpleAttributes(predicates, root, fClass, filter, input);
            }
        });

        return predicates.toArray(new Predicate[0]);
    }


    protected void addSimpleAttributes(List<Predicate> predicates, From<?, ?> root, Class<?> attributeType, String filter, String input) {
        CriteriaBuilder cb = criteriaBuilder();
        if (attributeType.equals(String.class)) {
            predicates.add(cb.like(cb.lower(root.get(filter)), String.format(LIKE_QUERY_PATTERN, input)));
        } else if (Number.class.isAssignableFrom(attributeType)) {
            try {
                Number number = NumberFormat.getInstance().parse(input);
                predicates.add(cb.equal(root.get(filter), number));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (attributeType.equals(Boolean.class)) {
            try {
                Boolean booleanAttr = Boolean.valueOf(input);
                predicates.add(cb.equal(root.get(filter), booleanAttr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (attributeType.equals(Date.class)) {
            try {
                Date dateAttr = Date.valueOf(input);
                predicates.add(cb.equal(root.get(filter), dateAttr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
