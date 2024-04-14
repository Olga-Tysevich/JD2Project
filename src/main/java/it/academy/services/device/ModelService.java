package it.academy.services.device;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.device.req.ModelDTO;
import it.academy.dto.device.resp.ModelListDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;

import java.util.List;

public interface ModelService {

    void createModel(ModelDTO model) throws AccessDenied;

    void updateModel(ModelDTO model) throws AccessDenied;

    ModelDTO findModel(long id);

    List<ModelDTO> findModels(AccountDTO accountDTO);

    ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber) throws DeviceTypesNotFound, BrandsNotFound;

    ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber, String filter, String input) throws DeviceTypesNotFound, BrandsNotFound;
}
