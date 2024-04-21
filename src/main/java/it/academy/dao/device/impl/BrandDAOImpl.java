package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.Brand;
import it.academy.utils.dao.TransactionManger;

public class BrandDAOImpl extends ComponentDAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

    public BrandDAOImpl(TransactionManger manger) {
        super(manger, Brand.class);
    }

}
