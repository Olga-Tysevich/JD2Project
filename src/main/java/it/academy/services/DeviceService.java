package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.device.DeviceTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface DeviceService {

    RespDTO<BrandDTOReq> addBrand(BrandDTOReq req);

    RespDTO<BrandDTOReq> changeBrand(BrandDTOReq req);

    RespListDTO<BrandDTOReq> findBrands();

    RespListDTO<BrandDTOReq> findBrands(int pageNumber, int listSize);

    RespListDTO<BrandDTOReq> findBrands(ParametersForSearchDTO parameters);

    RespDTO<DeviceTypeDTOReq> addDeviceType(DeviceTypeDTOReq req);

    RespDTO<DeviceTypeDTOReq> changeDeviceType(DeviceTypeDTOReq req);

    RespListDTO<DeviceTypeDTOReq> findDeviceType();

    RespListDTO<DeviceTypeDTOReq> findDeviceType(int pageNumber, int listSize);

    RespListDTO<DeviceTypeDTOReq> findDeviceType(ParametersForSearchDTO parameters);

}
