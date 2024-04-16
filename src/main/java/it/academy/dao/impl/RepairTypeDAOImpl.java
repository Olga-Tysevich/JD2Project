package it.academy.dao.impl;

import it.academy.dao.RepairTypeDAO;
import it.academy.entities.RepairType;
import it.academy.utils.dao.TransactionManger;

public class RepairTypeDAOImpl extends DAOImpl<RepairType, Long> implements RepairTypeDAO {

    public RepairTypeDAOImpl() {
        super(RepairType.class);
    }

    public RepairTypeDAOImpl(TransactionManger manger) {
        super(manger, RepairType.class);
    }
}
