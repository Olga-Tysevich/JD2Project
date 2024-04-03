package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface DefectService {

    RespDTO<DefectDTOReq> addDefect(DefectDTOReq req);

    RespDTO<DefectDTOReq> changeDefect(DefectDTOReq req);

    RespListDTO<DefectDTOReq> findDefect();

    RespListDTO<DefectDTOReq> findDefect(int pageNumber, int listSize);

    RespListDTO<DefectDTOReq> findDefect(ParametersForSearchDTO parameters);

}
