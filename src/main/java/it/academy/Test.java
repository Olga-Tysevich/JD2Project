package it.academy;


import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.entities.account.ServiceAccount;

public class Test {
    public static void main(String[] args) {
//        EntityManager manager = HibernateUtil.getEntityManager();

        String d ="1.2";


        AccountDAOImpl<ServiceAccount> s = new AccountDAOImpl<>(ServiceAccount.class);
        System.out.println(s);
    }
}
