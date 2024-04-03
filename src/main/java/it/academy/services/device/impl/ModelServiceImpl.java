package it.academy.services.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.ModelDTOReq;
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
    public RespDTO<ModelDTOReq> addModel(ModelDTOReq req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertDTOReqToEntity(req));
        Supplier<Model> save = () -> modelDAO.create(model);
        RespDTO<ModelDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> ModelConverter.convertToDTOReq(transactionManger.execute(save)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ModelDTOReq> changeModel(ModelDTOReq req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertDTOReqToEntity(req));
        Supplier<Model> update = () -> modelDAO.update(model);
        RespDTO<ModelDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> ModelConverter.convertToDTOReq(transactionManger.execute(update)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels() {
        List<Model> result = transactionManger.execute(() -> modelDAO.findAll());

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(int pageNumber, int listSize) {
        List<Model> result = transactionManger.execute(() -> modelDAO.findForPage(pageNumber, listSize));

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Model> result = transactionManger.execute(() ->
                modelDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

}
