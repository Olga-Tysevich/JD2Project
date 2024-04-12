package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.dao.TransactionManger;

public class RepairTypeDAOImpl extends DAOImpl<RepairType, Long> implements RepairTypeDAO {

    public RepairTypeDAOImpl() {
        super(new TransactionManger(), RepairType.class);
    }

}
