package it.academy.services.device;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface DeviceTypeService {

    void createDeviceType(DeviceTypeDTO deviceType);

    void updateDeviceType(DeviceTypeDTO deviceType);

    DeviceTypeDTO findDeviceType(long id);

    List<DeviceTypeDTO> findDeviceTypes();

    ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber);

    ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input);

}
