package it.academy.dao.device.impl;

import it.academy.dao.device.BuyerDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.components.Buyer;

public class BuyerDAOImpl extends DAOImpl<Buyer, Long> implements BuyerDAO {

    public BuyerDAOImpl() {
        super(Buyer.class);

    }
}
