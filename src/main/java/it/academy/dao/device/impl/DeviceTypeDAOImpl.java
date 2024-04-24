package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.DeviceType;
import it.academy.utils.dao.TransactionManger;

public class DeviceTypeDAOImpl extends ComponentDAOImpl<DeviceType, Long> implements DeviceTypeDAO {

    public DeviceTypeDAOImpl(TransactionManger manger) {
        super(manger, DeviceType.class);
    }
}
