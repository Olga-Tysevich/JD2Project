package it.academy.dao.repair.impl;

import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.entities.repair.RepairType;
import it.academy.utils.TransactionManger;

import java.util.List;

import static it.academy.utils.constants.Constants.FIND_ACTIVE_REPAIR_TYPES;

public class RepairTypeDAOImpl extends ComponentDAOImpl<RepairType, Long> implements RepairTypeDAO {

    public RepairTypeDAOImpl(TransactionManger manger) {
        super(manger, RepairType.class);
    }

    @Override
    public List<RepairType> findAllActive() {
        return entityManager().createQuery(FIND_ACTIVE_REPAIR_TYPES, RepairType.class)
                .getResultList();
    }

}
