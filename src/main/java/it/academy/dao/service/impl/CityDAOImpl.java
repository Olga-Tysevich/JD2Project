package it.academy.dao.service.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.service.CityDAO;
import it.academy.entities.service_center.components.City;

public class CityDAOImpl extends DAOImpl<City, Long> implements CityDAO {

    public CityDAOImpl() {
        super(City.class);
    }

}
