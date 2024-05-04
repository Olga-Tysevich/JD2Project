package it.academy.utils.dao;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.function.Supplier;

import static it.academy.utils.constants.LoggerConstants.TRANSACTION_ERROR_PATTERN;

@Slf4j
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
            log.error(TRANSACTION_ERROR_PATTERN, e.getClass(), e.getMessage(), e.getStackTrace());
            rollback();
            throw e;
        }
        closeManager();
        return result;
    }

    public void beginTransaction() {
        if (!entityManager().getTransaction().isActive()) {
            entityManager().getTransaction().begin();
        }
    }

    public void commit() {
        if (entityManager().getTransaction().isActive()) {
            entityManager().getTransaction().commit();
            entityManager.close();
        }
    }

    public void rollback() {
        if (entityManager().getTransaction().isActive()
                || entityManager().getTransaction().getRollbackOnly()) {
            entityManager().getTransaction().commit();
        }
    }
}
