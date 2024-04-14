package it.academy.dao.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.entities.DeviceType;

public class DeviceTypeDAOImpl extends DAOImpl<DeviceType, Long> implements DeviceTypeDAO {

    public DeviceTypeDAOImpl() {
        super(DeviceType.class);
    }
    
}
