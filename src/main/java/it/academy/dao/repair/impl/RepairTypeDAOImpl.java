package it.academy.dao.repair.impl;

import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.entities.repair.RepairType;
import it.academy.utils.dao.TransactionManger;

public class RepairTypeDAOImpl extends ComponentDAOImpl<RepairType, Long> implements RepairTypeDAO {

    public RepairTypeDAOImpl(TransactionManger manger) {
        super(manger, RepairType.class);
    }

}
