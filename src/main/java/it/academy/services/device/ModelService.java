package it.academy.services.device;

import it.academy.dto.TablePage2;
import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelForChangeDTO;

import java.util.List;

public interface ModelService {

    void create(ChangeModelDTO model);

    void update(ChangeModelDTO model);

    void delete(long id);

    ModelForChangeDTO getForm();

    ModelForChangeDTO getForm(long id);

    List<ModelDTO> findAll();

    TablePage2<ModelDTO> findForPage(int pageNumber);

    TablePage2<ModelDTO> findByName(int pageNumber, String input);

    TablePage2<ModelDTO> findByComponentName(int pageNumber, String filter, String input);
}
