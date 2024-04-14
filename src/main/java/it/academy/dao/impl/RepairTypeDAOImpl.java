package it.academy.dao.impl;

import it.academy.dao.RepairTypeDAO;
import it.academy.entities.RepairType;

public class RepairTypeDAOImpl extends DAOImpl<RepairType, Long> implements RepairTypeDAO {

    public RepairTypeDAOImpl() {
        super(RepairType.class);
    }

}
