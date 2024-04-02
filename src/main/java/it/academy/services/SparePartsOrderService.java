package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.SparePartsOrderDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface SparePartsOrderService {

    RespDTO<SparePartsOrderDTOReq> addSparePartsOrder(SparePartsOrderDTOReq req);

    RespDTO<SparePartsOrderDTOReq> changeSparePartsOrder(SparePartsOrderDTOReq req);

    RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder();

    RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder(int pageNumber, int listSize);

    RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder(ParametersForSearchDTO parameters);


}
