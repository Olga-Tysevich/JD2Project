package it.academy.services.device;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;

import java.util.List;

public interface DeviceTypeService {

    void addDeviceType(DeviceTypeDTO deviceType);

    void updateDeviceType(DeviceTypeDTO deviceType);

    DeviceTypeDTO findDeviceType(long id);

    List<DeviceTypeDTO> findDeviceTypes();

    ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber);

    ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input);

}
