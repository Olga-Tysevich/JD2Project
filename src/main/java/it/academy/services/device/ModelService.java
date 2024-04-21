package it.academy.services.device;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.ModelForChangeDTO;

import java.util.List;

public interface ModelService {

    void createModel(ChangeModelDTO model);

    void updateModel(ChangeModelDTO model);

    ModelForChangeDTO getModelForm();

    ModelForChangeDTO getModelForm(long id);

    List<ModelDTO> findModels();

    ListForPage<ModelDTO> findModels(int pageNumber);

    ListForPage<ModelDTO> findModels(int pageNumber, String filter, String input);
}
