package it.academy.services.device;

import it.academy.dto.TablePage;
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

    TablePage<ModelDTO> findForPage(int pageNumber);

    TablePage<ModelDTO> findByName(int pageNumber, String input);

    TablePage<ModelDTO> findByComponentName(int pageNumber, String filter, String input);
}
