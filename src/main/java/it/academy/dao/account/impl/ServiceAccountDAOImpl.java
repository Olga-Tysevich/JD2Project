package it.academy.dao.account.impl;

import it.academy.dao.account.ServiceAccountDAO;
import it.academy.entities.account.ServiceAccount;

public class ServiceAccountDAOImpl extends AccountDAOImpl<ServiceAccount> implements ServiceAccountDAO {

    public ServiceAccountDAOImpl() {
        super(ServiceAccount.class);
    }

}
