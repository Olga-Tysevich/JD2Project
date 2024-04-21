package it.academy.dao.account.impl;

import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.ServiceCenter;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static it.academy.utils.constants.Constants.*;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl() {
        super(ServiceCenter.class);
    }

    public ServiceCenterDAOImpl(TransactionManger manger) {
        super(manger, ServiceCenter.class);
    }

    @Override
    public boolean checkIfServiceCenterExist(ServiceCenter serviceCenter) {
        TypedQuery<ServiceCenter> find = entityManager().createQuery(CHECK_SERVICE_CENTER, ServiceCenter.class);
        find.setParameter(OBJECT_ID, serviceCenter.getId());
        find.setParameter(OBJECT_NAME, serviceCenter.getServiceName());
        try {
            ServiceCenter result = find.getSingleResult();
            return result == null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
