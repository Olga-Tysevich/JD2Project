package it.academy.dao.impl;

import it.academy.dao.ServiceCenterDAO;
import it.academy.entities.service_center.ServiceCenter;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl() {
        super(ServiceCenter.class);
    }

}
