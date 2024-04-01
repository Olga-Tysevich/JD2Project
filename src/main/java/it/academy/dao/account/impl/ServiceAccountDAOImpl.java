package it.academy.dao.account.impl;


import it.academy.dao.account.ServiceAccountDAO;
import it.academy.entities.account.RepairWorkshopAccount;

public class ServiceAccountDAOImpl extends AccountDAOImpl<RepairWorkshopAccount> implements ServiceAccountDAO {

    public ServiceAccountDAOImpl() {
        super(RepairWorkshopAccount.class);
    }

}
