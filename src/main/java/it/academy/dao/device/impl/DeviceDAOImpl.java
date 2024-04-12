package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.device.Device;
import it.academy.utils.dao.TransactionManger;

public class DeviceDAOImpl extends DAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl() {
        super(Device.class);
    }
}
