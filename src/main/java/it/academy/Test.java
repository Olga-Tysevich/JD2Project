package it.academy;

import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceAccount;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;

public class Test {
    public static void main(String[] args) {
        EntityManager manager = HibernateUtil.getEntityManager();

    }
}
