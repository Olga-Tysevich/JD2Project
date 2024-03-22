package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairStatusDAO;
import it.academy.entities.repair.components.RepairStatus;

public class RepairStatusDAOImpl extends DAOImpl<RepairStatus, Long> implements RepairStatusDAO {

    public RepairStatusDAOImpl() {
        super(RepairStatus.class);
    }

}
