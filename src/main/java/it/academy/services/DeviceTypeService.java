package it.academy.services;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface DeviceTypeService {

    void createDeviceType(DeviceTypeDTO deviceType) throws AccessDenied;

    void updateDeviceType(DeviceTypeDTO deviceType) throws AccessDenied;

    DeviceTypeDTO findDeviceType(long id);

    List<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO);

    ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber);

    ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber, String filter, String input);

}
