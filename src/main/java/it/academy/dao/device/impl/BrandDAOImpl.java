package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.components.Brand;
import it.academy.utils.dao.TransactionManger;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(new TransactionManger(), Brand.class);
    }

}
