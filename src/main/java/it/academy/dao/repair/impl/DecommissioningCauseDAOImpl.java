package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.DecommissioningCauseDAO;
import it.academy.entities.repair.decommissioning.DecommissioningCause;

public class DecommissioningCauseDAOImpl extends DAOImpl<DecommissioningCause, Long> implements DecommissioningCauseDAO {

    public DecommissioningCauseDAOImpl() {
        super(DecommissioningCause.class);
    }

}
