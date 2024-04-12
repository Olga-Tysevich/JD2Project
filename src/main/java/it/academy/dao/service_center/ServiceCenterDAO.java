package it.academy.dao.service_center;

import it.academy.dao.DAO;
import it.academy.entities.service_center.ServiceCenter;

public interface ServiceCenterDAO extends DAO<ServiceCenter, Long> {

    ServiceCenter findByEmailAndServiceName(String email, String serviceName);

}
