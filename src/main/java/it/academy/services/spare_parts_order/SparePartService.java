package it.academy.services.spare_parts_order;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface SparePartService {

    RespDTO<SparePartDTOReq> addSparePart(SparePartDTOReq req);

    RespDTO<SparePartDTOReq> changeSparePart(SparePartDTOReq req);

    RespListDTO<SparePartDTOReq> findSparePart();

    RespListDTO<SparePartDTOReq> findSparePart(int pageNumber, int listSize);

    RespListDTO<SparePartDTOReq> findSparePart(ParametersForSearchDTO parameters);

}
