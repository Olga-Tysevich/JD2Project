package it.academy.dao.service.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.service.ServiceCenterDAO;
import it.academy.entities.service_center.ServiceCenter;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl() {
        super(ServiceCenter.class);
    }

}
