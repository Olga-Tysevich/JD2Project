package it.academy.dao.device.impl;

import it.academy.dao.device.SalesmanDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.components.Salesman;

public class SalesmanDAOImpl extends DAOImpl<Salesman, Long> implements SalesmanDAO {

    public SalesmanDAOImpl() {
        super(Salesman.class);
    }
}
