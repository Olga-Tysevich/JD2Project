package it.academy.dao.impl;

import it.academy.dao.DeviceDAO;
import it.academy.entities.Device;

public class DeviceDAOImpl extends DAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl() {
        super(Device.class);
    }
}
