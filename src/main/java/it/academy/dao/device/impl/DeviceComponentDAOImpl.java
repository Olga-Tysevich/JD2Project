package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceComponentDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.TypedQuery;

import static it.academy.utils.constants.Constants.*;

public abstract class DeviceComponentDAOImpl<T, R> extends DAOImpl<T, R> implements DeviceComponentDAO<T, R>  {
    private Class<T> clazz;

    public DeviceComponentDAOImpl(Class<T> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    public DeviceComponentDAOImpl(TransactionManger manger, Class<T> clazz) {
        super(manger, clazz);
        this.clazz = clazz;
    }

    @Override
    public boolean checkIfComponentExist(R id, String name) {
        String checkQuery = String.format(CHECK_DEVICE_COMPONENT, clazz.getSimpleName());
        TypedQuery<Long> find = entityManager().createQuery(checkQuery, Long.class);
        find.setParameter(OBJECT_ID, id);
        find.setParameter(OBJECT_NAME, name);
        return find.getSingleResult() != 0;

    }



}
