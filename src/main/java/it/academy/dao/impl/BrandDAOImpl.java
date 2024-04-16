package it.academy.dao.impl;

import it.academy.dao.BrandDAO;
import it.academy.entities.Brand;
import it.academy.utils.dao.TransactionManger;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

    public BrandDAOImpl(TransactionManger manger) {
        super(manger, Brand.class);
    }
}
