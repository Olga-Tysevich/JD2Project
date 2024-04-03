package it.academy.dao.account.impl;


import it.academy.dao.account.RepairWorkshopAccountDAO;
import it.academy.entities.account.RepairWorkshopAccount;

public class RepairWorkshopAccountDAOImpl extends AccountDAOImpl<RepairWorkshopAccount> implements RepairWorkshopAccountDAO {

    public RepairWorkshopAccountDAOImpl() {
        super(RepairWorkshopAccount.class);
    }

}
