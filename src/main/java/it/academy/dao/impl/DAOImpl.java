package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.entities.repair.RepairType;
import it.academy.utils.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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


    @Override
    public List<T> findAllByPageAndSearch(Integer currentPage, Integer itemsPerPage, String searchQuery) {
        EntityManager em = entityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(clazz);
        Root<T> root = criteria.from(clazz);

        criteria.select(root);

        if (searchQuery != null && !searchQuery.isEmpty()) {
            Predicate searchPredicate = createSearchPredicate2(cb, root, searchQuery);
            criteria.where(searchPredicate);
        }

        criteria.orderBy(cb.asc(root.get(OBJECT_ID)));
        TypedQuery<T> typedQuery = em.createQuery(criteria);
        int offset = (currentPage - 1) * itemsPerPage;
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(itemsPerPage);
        List<T> resultList = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();
        return resultList;
    }

    @Override
    public Integer getNumberOfRows(String searchQuery) {
        EntityManager em = entityManager();
        em.getTransaction().begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> root = criteria.from(clazz);

        criteria.select(builder.count(root));

        if (searchQuery != null && !searchQuery.isEmpty()) {
            Predicate searchPredicate = createSearchPredicate(builder, root, searchQuery);
            criteria.where(searchPredicate);
        }

        Long count = em.createQuery(criteria).getSingleResult();

        em.getTransaction().commit();
        em.close();
        return count.intValue();
    }

    /**
     * Creates a search predicate to search for entities that match any of the given search terms in their
     * string or number fields.
     *
     * @param cb          the criteria builder to use to create the predicates
     * @param root        the root of the criteria query
     * @param searchQuery the search query containing the search terms
     * @return a predicate that can be used to filter entities based on the search terms
     */
    private Predicate createSearchPredicate(CriteriaBuilder cb, Root<T> root, String searchQuery) {
        String[] searchTerms = searchQuery.split(REGEX);
        List<Predicate> searchPredicates = new ArrayList<>();
        for (String term : searchTerms) {
            List<Predicate> fieldPredicates = new ArrayList<>();

            Set<SingularAttribute<T, ?>> a = root.getModel().getDeclaredSingularAttributes();
            for (SingularAttribute<? super T, ?> attribute : root.getModel().getDeclaredSingularAttributes()) {
                Class<?> attributeType = attribute.getJavaType();
                if (attributeType.equals(String.class)) {
                    fieldPredicates.add(cb.like(cb.lower(root.get(attribute.getName())),
                            LIKE_EXPRESSION + term.toLowerCase() + LIKE_EXPRESSION));
                } else if (Number.class.isAssignableFrom(attributeType)) {
                    try {
                        Number number = NumberFormat.getInstance().parse(term);
                        fieldPredicates.add(cb.equal(root.get(attribute.getName()), number));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            searchPredicates.add(cb.or(fieldPredicates.toArray(new Predicate[0])));
        }
        return cb.and(searchPredicates.toArray(new Predicate[0]));
    }

    protected <S extends Enum<S>> Predicate createSearchPredicate2(CriteriaBuilder cb, Root<?> root, String searchQuery) {
        String[] searchTerms = searchQuery.split(REGEX);
        List<Predicate> searchPredicates = new ArrayList<>();

        for (String term : searchTerms) {
            List<Predicate> fieldPredicates = new ArrayList<>();
            Set<? extends SingularAttribute<?, ?>> attributes = root.getModel().getDeclaredSingularAttributes();

            for (SingularAttribute attribute : attributes) {
                Class<?> attributeType = attribute.getJavaType();
                addSimpleAttributes(fieldPredicates, attribute, root, attributeType, term);
            }

            searchPredicates.add(criteriaBuilder().or(fieldPredicates.toArray(new Predicate[0])));
        }

        return criteriaBuilder().and(searchPredicates.toArray(new Predicate[0]));
    }

    protected void addSimpleAttributes(List<Predicate> predicates, SingularAttribute attribute, Root<?> root, Class<?> attributeType, String term) {
        CriteriaBuilder cb = criteriaBuilder();
        if (attributeType.equals(String.class)) {
            predicates.add(cb.like(cb.lower(root.get(attribute.getName())), LIKE_EXPRESSION + term.toLowerCase() + LIKE_EXPRESSION));
        } else if (Number.class.isAssignableFrom(attributeType)) {
            try {
                Number number = NumberFormat.getInstance().parse(term);
                predicates.add(cb.equal(root.get(attribute.getName()), number));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (attributeType.equals(Boolean.class)) {
            try {
                Boolean booleanAttr = Boolean.valueOf(term);
                predicates.add(cb.equal(root.get(attribute.getName()), booleanAttr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (attributeType.equals(Date.class)) {
            try {
                Date dateAttr = Date.valueOf(term);
                predicates.add(cb.equal(root.get(attribute.getName()), dateAttr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
