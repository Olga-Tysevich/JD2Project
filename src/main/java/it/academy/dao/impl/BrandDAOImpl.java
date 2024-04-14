package it.academy.dao.impl;

import it.academy.dao.BrandDAO;
import it.academy.entities.device.components.Brand;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

}
