package it.academy.dao.impl;

import it.academy.dao.DeviceDAO;
import it.academy.entities.Device;
import it.academy.utils.dao.TransactionManger;

public class DeviceDAOImpl extends DAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl() {
        super(Device.class);
    }

    public DeviceDAOImpl(TransactionManger manger) {
        super(manger, Device.class);
    }
}
