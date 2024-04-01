package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface ServiceCenterService {

    RespDTO<ServiceCenterDTOReq> addServiceCenter(ServiceCenterDTOReq req);

    RespDTO<ServiceCenterDTOReq> changeServiceCenter(ServiceCenterDTOReq req);

    RespListDTO<ServiceCenterDTOReq> findServiceCenters();

    RespListDTO<ServiceCenterDTOReq> findServiceCenters(int pageNumber, int listSize);

    RespListDTO<ServiceCenterDTOReq> findServiceCenters(ParametersForSearchDTO parameters);

}
