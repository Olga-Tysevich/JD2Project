package it.academy.services;

import it.academy.dto.device.ModelDTO;

import java.util.List;

public interface ModelService {

    ModelDTO findModel(long id);

    List<ModelDTO> findModelsByBrandId(long brandId);

}
