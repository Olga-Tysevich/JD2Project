package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceDAO;
import it.academy.entities.Device;
import it.academy.utils.dao.TransactionManger;

public class DeviceDAOImpl extends DeviceComponentDAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl() {
        super(Device.class);
    }

    public DeviceDAOImpl(TransactionManger manger) {
        super(manger, Device.class);
    }
}
