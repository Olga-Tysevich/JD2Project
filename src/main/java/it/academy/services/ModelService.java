package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.ModelDTO;

import java.util.List;

public interface ModelService {

    void addModel(ModelDTO model);

    void updateModel(ModelDTO model);

    ModelDTO findModel(long id);

    List<ModelDTO> findModels();

    ListForPage<ModelDTO> findModels(int pageNumber);

    ListForPage<ModelDTO> findModels(int pageNumber, String filter, String input);
}
