package it.academy.utils.dao;

import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class TransactionManger {
    private static volatile TransactionManger instance;
    private ReentrantLock lock = new ReentrantLock();
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

    public EntityManager entityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            return entityManager;
        }
        lock.lock();
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = HibernateUtil.getEntityManager();
        }
        lock.unlock();
        return entityManager;
    }

    public CriteriaBuilder criteriaBuilder() {
        return entityManager().getCriteriaBuilder();
    }

    public void closeManager() {
        lock.lock();
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        lock.unlock();
    }

    public <T> T execute(Supplier<T> method) {
        lock.lock();
        beginTransaction();
        T result = null;
        try {
            result = method.get();
            commit();
        } catch (Exception e) {
            rollback();
        }
        entityManager().close();
        lock.unlock();
        return result;
    }

    private void beginTransaction() {
        entityManager().getTransaction().begin();
    }

    private void commit() {
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
