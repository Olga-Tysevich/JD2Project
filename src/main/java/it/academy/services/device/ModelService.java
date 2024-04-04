package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.ModelDTO;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

import java.util.List;

public interface ModelService {

    RespDTO<ModelDTO> addModel(ModelDTO req);

    RespDTO<ModelDTO> changeModel(ModelDTO req);

    RespListDTO<ModelDTO> findModels();

    List<ModelDTO> findModelsByBrandId(long brandId);

    RespListDTO<ModelDTO> findModels(int pageNumber, int listSize);

    RespListDTO<ModelDTO> findModels(ParametersForSearchDTO parameters);

}
