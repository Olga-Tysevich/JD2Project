package it.academy.services;

import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.ModelListDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;

import java.util.List;

public interface ModelService {

    void createModel(ChangeModelDTO model) throws AccessDenied;

    void updateModel(ChangeModelDTO model) throws AccessDenied;

    ModelDTO findModel(long id);

    List<ModelDTO> findModels(AccountDTO accountDTO);

    ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber) throws DeviceTypesNotFound, BrandsNotFound;

    ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber, String filter, String input) throws DeviceTypesNotFound, BrandsNotFound;
}
