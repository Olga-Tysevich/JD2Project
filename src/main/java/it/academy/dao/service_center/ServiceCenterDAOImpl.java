package it.academy.dao.service_center;

import it.academy.dao.DAOImpl;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Objects;

import static it.academy.utils.Constants.*;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl() {
        super(ServiceCenter.class);
    }

    @Override
    public ServiceCenter findByEmailAndServiceName(String email, String serviceName) {
        CriteriaQuery<ServiceCenter> find = criteriaBuilder().createQuery(ServiceCenter.class);
        Root<ServiceCenter> root = find.from(ServiceCenter.class);

        find.select(root)
                .where(criteriaBuilder().equal(root.get(SERVICE_CENTER_REQUISITES).get(SERVICE_CENTER_EMAIL), email),
                        criteriaBuilder().equal(root.get(SERVICE_CENTER_NAME), serviceName));

        return entityManager()
                .createQuery(find)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
