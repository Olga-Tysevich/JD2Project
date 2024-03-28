package it.academy.dao.service_center.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.service_center.CountryDAO;
import it.academy.entities.service_center.components.Country;

public class CountryDAOImpl extends DAOImpl<Country, Long> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

}
