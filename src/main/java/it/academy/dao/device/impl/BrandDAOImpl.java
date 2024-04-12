package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.device.components.Brand;
import it.academy.utils.dao.TransactionManger;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(new TransactionManger(), Brand.class);
    }

    public BrandDAOImpl(TransactionManger manger) {
        super(manger, Brand.class);
    }
}
