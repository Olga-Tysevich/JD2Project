package it.academy.utils.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.function.Supplier;

public class TransactionManger {
    private static volatile TransactionManger instance;
    private EntityManager entityManager;

    private TransactionManger() {
    }

    public static TransactionManger getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (TransactionManger.class) {
            if (instance == null) {
                instance = new TransactionManger();
            }
            return instance;
        }
    }

    public synchronized EntityManager entityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            return entityManager;
        }
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = HibernateUtil.getEntityManager();
        }
        return entityManager;
    }

    public synchronized CriteriaBuilder criteriaBuilder() {
        return entityManager().getCriteriaBuilder();
    }

    public synchronized void closeManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public synchronized <T> T execute(Supplier<T> method) {
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

    public synchronized void beginTransaction() {
        entityManager().getTransaction().begin();
    }

    public synchronized void commit() {
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
