package it.academy.dao.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.entities.DeviceType;
import it.academy.utils.dao.TransactionManger;

public class DeviceTypeDAOImpl extends DAOImpl<DeviceType, Long> implements DeviceTypeDAO {

    public DeviceTypeDAOImpl() {
        super(DeviceType.class);
    }

    public DeviceTypeDAOImpl(TransactionManger manger) {
        super(manger, DeviceType.class);
    }
}
