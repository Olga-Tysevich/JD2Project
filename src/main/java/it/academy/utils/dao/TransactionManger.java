package it.academy.utils.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.function.Supplier;

public class TransactionManger {
    private EntityManager entityManager;


    public EntityManager entityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            return entityManager;
        }
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = HibernateUtil.getEntityManager();
        }
        return entityManager;
    }

    public CriteriaBuilder criteriaBuilder() {
        return entityManager().getCriteriaBuilder();
    }

    public void closeManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public <T> T execute(Supplier<T> method) {
        beginTransaction();
        T result;
        try {
            result = method.get();
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
        entityManager().close();
        return result;
    }

    public void beginTransaction() {
        entityManager().getTransaction().begin();
    }

    public void commit() {
        if (entityManager().getTransaction().isActive()) {
            entityManager().getTransaction().commit();
        }
    }

    private void rollback() {
        if (entityManager().getTransaction().isActive()
                || entityManager().getTransaction().getRollbackOnly()) {
            entityManager().getTransaction().commit();
        }
    }
}
