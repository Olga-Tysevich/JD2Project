package it.academy.dao.repair_workshop.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.repair_workshop.CountryDAO;
import it.academy.entities.repair_workshop.components.Country;

public class CountryDAOImpl extends DAOImpl<Country, Long> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

}
