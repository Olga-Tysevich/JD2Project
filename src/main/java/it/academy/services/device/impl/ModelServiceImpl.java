package it.academy.services.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.ModelDTO;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.components.Model;
import it.academy.services.device.ModelService;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public RespDTO<ModelDTO> addModel(ModelDTO req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertDTOToEntity(req));
        Supplier<Model> save = () -> modelDAO.create(model);
        RespDTO<ModelDTO> resp = ExceptionManager.getObjectSaveResult(() -> ModelConverter.convertToDTO(transactionManger.execute(save)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ModelDTO> changeModel(ModelDTO req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertDTOToEntity(req));
        Supplier<Model> update = () -> modelDAO.update(model);
        RespDTO<ModelDTO> resp = ExceptionManager.getObjectUpdateResult(() -> ModelConverter.convertToDTO(transactionManger.execute(update)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTO> findModels() {
        List<Model> result = transactionManger.execute(() -> modelDAO.findAll());

        RespListDTO<ModelDTO> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public List<ModelDTO> findModelsByBrandId(long brandId) {
        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
        List<ModelDTO> resp = ModelConverter.convertListToDTO(models);
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTO> findModels(int pageNumber, int listSize) {
        List<Model> result = transactionManger.execute(() -> modelDAO.findForPage(pageNumber, listSize));

        RespListDTO<ModelDTO> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTO> findModels(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Model> result = transactionManger.execute(() ->
                modelDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<ModelDTO> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

}
