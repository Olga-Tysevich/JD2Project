package it.academy.dao.account.impl;


import it.academy.dao.account.ServiceAccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.ServiceAccount;

public class ServiceAccountDAOImpl extends DAOImpl<ServiceAccount, Long> implements ServiceAccountDAO {

    public ServiceAccountDAOImpl() {
        super(ServiceAccount.class);
    }

}
