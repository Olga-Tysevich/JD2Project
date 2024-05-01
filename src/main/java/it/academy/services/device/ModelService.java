package it.academy.services.device;

import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.TablePage;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelForChangeDTO;

import java.util.List;

public interface ModelService {

    void createModel(ChangeModelDTO model);

    void updateModel(ChangeModelDTO model);

    void deleteModel(long id);

    ModelForChangeDTO getModelForm();

    ModelForChangeDTO getModelForm(long id);

    List<ModelDTO> findModels();

    TablePage<ModelDTO> findModels(int pageNumber, String filter, String input);
}
