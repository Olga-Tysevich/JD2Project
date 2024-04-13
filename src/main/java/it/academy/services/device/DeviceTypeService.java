package it.academy.services.device;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface DeviceTypeService {

    void createDeviceType(DeviceTypeDTO deviceType) throws AccessDenied;

    void updateDeviceType(DeviceTypeDTO deviceType) throws AccessDenied;

    DeviceTypeDTO findDeviceType(long id);

    List<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO);

    ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber);

    ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO,int pageNumber, String filter, String input);

}
