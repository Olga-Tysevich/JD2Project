package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.components.DeviceType;

import java.util.List;

public interface DeviceTypeDAO extends DAO<DeviceType, Long> {

    List<DeviceType> findBySparePartId(long id);

}
