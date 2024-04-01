package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.*;
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

    RespDTO<ModelDTOReq> addModel(ModelDTOReq req);

    RespDTO<ModelDTOReq> changeModel(ModelDTOReq req);

    RespListDTO<ModelDTOReq> findModels();

    RespListDTO<ModelDTOReq> findModels(int pageNumber, int listSize);

    RespListDTO<ModelDTOReq> findModels(ParametersForSearchDTO parameters);

    RespDTO<DeviceDTOReq> addDevice(DeviceDTOReq req);

    RespDTO<DeviceDTOReq> changeDevice(DeviceDTOReq req);

    RespListDTO<DeviceDTOReq> findDevices();

    RespListDTO<DeviceDTOReq> findDevices(int pageNumber, int listSize);

    RespListDTO<DeviceDTOReq> findDevices(ParametersForSearchDTO parameters);

    RespDTO<DefectDTOReq> addDefect(DefectDTOReq req);

    RespDTO<DefectDTOReq> changeDefect(DefectDTOReq req);

    RespListDTO<DefectDTOReq> findDefect();

    RespListDTO<DefectDTOReq> findDefect(int pageNumber, int listSize);

    RespListDTO<DefectDTOReq> findDefect(ParametersForSearchDTO parameters);

}
