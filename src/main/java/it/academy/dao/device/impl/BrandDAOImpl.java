package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.device.components.Brand;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

}
