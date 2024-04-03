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

    RespDTO<DeviceDTOReq> addDevice(DeviceDTOReq req);

    RespDTO<DeviceDTOReq> changeDevice(DeviceDTOReq req);

    RespListDTO<DeviceDTOReq> findDevices();

    RespListDTO<DeviceDTOReq> findDevices(int pageNumber, int listSize);

    RespListDTO<DeviceDTOReq> findDevices(ParametersForSearchDTO parameters);

}
