package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface ModelService {

    RespDTO<ModelDTOReq> addModel(ModelDTOReq req);

    RespDTO<ModelDTOReq> changeModel(ModelDTOReq req);

    RespListDTO<ModelDTOReq> findModels();

    RespListDTO<ModelDTOReq> findModels(int pageNumber, int listSize);

    RespListDTO<ModelDTOReq> findModels(ParametersForSearchDTO parameters);

}
