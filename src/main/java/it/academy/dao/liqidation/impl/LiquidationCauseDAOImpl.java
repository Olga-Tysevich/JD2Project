package it.academy.dao.liqidation.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.liqidation.LiquidationCauseDAO;
import it.academy.entities.liquidation.LiquidationCause;

public class LiquidationCauseDAOImpl extends DAOImpl<LiquidationCause, Long> implements LiquidationCauseDAO {

    public LiquidationCauseDAOImpl() {
        super(LiquidationCause.class);
    }

}
