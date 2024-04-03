package it.academy.dao.repair_workshop.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.repair_workshop.CityDAO;
import it.academy.entities.repair_workshop.components.City;

public class CityDAOImpl extends DAOImpl<City, Long> implements CityDAO {

    public CityDAOImpl() {
        super(City.class);
    }

}
