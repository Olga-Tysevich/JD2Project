package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.DeviceType;
import it.academy.utils.dao.TransactionManger;

public class DeviceTypeDAOImpl extends DeviceComponentDAOImpl<DeviceType, Long> implements DeviceTypeDAO {

    public DeviceTypeDAOImpl() {
        super(DeviceType.class);
    }

    public DeviceTypeDAOImpl(TransactionManger manger) {
        super(manger, DeviceType.class);
    }
}
