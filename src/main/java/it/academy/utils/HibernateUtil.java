package it.academy.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private HibernateUtil() {
    }

    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("project");

    public static synchronized EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static synchronized void close() {
        FACTORY.close();
    }
}
