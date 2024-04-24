package it.academy.dao.account.impl;

import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.ServiceCenter;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static it.academy.utils.constants.Constants.*;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl(TransactionManger manger) {
        super(manger, ServiceCenter.class);
    }

    @Override
    public boolean checkIfServiceCenterExist(long id, String name) {
        TypedQuery<Long> find = entityManager().createQuery(CHECK_SERVICE_CENTER, Long.class);
        find.setParameter(OBJECT_ID, id);
        find.setParameter(OBJECT_NAME, name);

        return find.getSingleResult() != 0;
    }
}
