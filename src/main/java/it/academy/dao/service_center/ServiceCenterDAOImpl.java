package it.academy.dao.service_center;

import it.academy.dao.DAOImpl;
import it.academy.entities.service_center.ServiceCenter;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl() {
        super(ServiceCenter.class);
    }
    
}
