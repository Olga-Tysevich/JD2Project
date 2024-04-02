package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.LiquidationCauseDAO;
import it.academy.entities.repair.decommissioning.LiquidationCause;

public class LiquidationCauseDAOImpl extends DAOImpl<LiquidationCause, Long> implements LiquidationCauseDAO {

    public LiquidationCauseDAOImpl() {
        super(LiquidationCause.class);
    }

}
