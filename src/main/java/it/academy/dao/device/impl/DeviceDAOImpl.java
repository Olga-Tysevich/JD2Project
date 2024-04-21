package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.Device;
import it.academy.utils.dao.TransactionManger;

public class DeviceDAOImpl extends ComponentDAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl() {
        super(Device.class);
    }

    public DeviceDAOImpl(TransactionManger manger) {
        super(manger, Device.class);
    }
}
