package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.entities.Brand;
import it.academy.utils.dao.TransactionManger;

public class BrandDAOImpl extends DeviceComponentDAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

    public BrandDAOImpl(TransactionManger manger) {
        super(manger, Brand.class);
    }

}
