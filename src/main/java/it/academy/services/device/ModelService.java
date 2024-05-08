package it.academy.services.device;

import it.academy.dto.TablePage2;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelFormDTO;

import java.util.List;

public interface ModelService {

    ModelDTO create(ModelDTO model);

    ModelDTO update(ModelDTO model);

    void delete(long id);

    ModelFormDTO getForm();

    ModelDTO find(long id);

    List<ModelDTO> findAll();

    TablePage2<ModelDTO> findForPage(int pageNumber);

    TablePage2<ModelDTO> findByName(int pageNumber, String input);

    TablePage2<ModelDTO> findByComponentName(int pageNumber, String filter, String input);
}
