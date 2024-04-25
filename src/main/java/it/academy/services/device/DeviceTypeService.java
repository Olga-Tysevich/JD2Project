package it.academy.services.device;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.ListForPage;
import java.util.List;

public interface DeviceTypeService {

    void createDeviceType(DeviceTypeDTO deviceType);

    void updateDeviceType(DeviceTypeDTO deviceType);

    void deleteDeviceType(long id);

    DeviceTypeDTO findDeviceType(long id);

    List<DeviceTypeDTO> findDeviceTypes();

    ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input);

}
