package it.academy.services.device;

import it.academy.dto.TablePage;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelFormDTO;
import java.util.List;
import java.util.Map;

public interface ModelService {

    ModelDTO create(ModelDTO model);

    ModelDTO update(ModelDTO model);

    void delete(long id);

    ModelFormDTO getForm();

    ModelDTO find(long id);

    List<ModelDTO> findAll();

    TablePage<ModelDTO> findForPage(int pageNumber, Map<String, String> userInput);
}
