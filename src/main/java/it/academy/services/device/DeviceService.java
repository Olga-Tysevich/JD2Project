package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.*;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface DeviceService {

    RespDTO<DeviceTypeDTOReq> addDeviceType(DeviceTypeDTOReq req);

    RespDTO<DeviceTypeDTOReq> changeDeviceType(DeviceTypeDTOReq req);

    RespListDTO<DeviceTypeDTOReq> findDeviceType();

    RespListDTO<DeviceTypeDTOReq> findDeviceType(int pageNumber, int listSize);

    RespListDTO<DeviceTypeDTOReq> findDeviceType(ParametersForSearchDTO parameters);

    RespDTO<DeviceDTO> addDevice(DeviceDTO req);

    RespDTO<DeviceDTO> changeDevice(DeviceDTO req);

    RespListDTO<DeviceDTO> findDevices();

    RespListDTO<DeviceDTO> findDevices(int pageNumber, int listSize);

    RespListDTO<DeviceDTO> findDevices(ParametersForSearchDTO parameters);

}
