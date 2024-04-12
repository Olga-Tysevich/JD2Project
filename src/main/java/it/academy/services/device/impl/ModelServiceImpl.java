package it.academy.services.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.req.ModelDTO;
import it.academy.entities.device.components.Model;
import it.academy.services.device.ModelService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = new TransactionManger();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public void addModel(ModelDTO model) {
        Model result = ModelConverter.convertDTOToEntity(model);
        transactionManger.execute(() -> modelDAO.create(result));
    }

    @Override
    public void updateModel(ModelDTO model) {
        Model result = ModelConverter.convertDTOToEntity(model);
        transactionManger.execute(() -> modelDAO.update(result));
    }

    @Override
    public ModelDTO findModel(long id) {
        Model deviceType = transactionManger.execute(() -> modelDAO.find(id));
        return ModelConverter.convertToDTO(deviceType);
    }

    @Override
    public List<ModelDTO> findModels() {
        List<Model> repairs = transactionManger.execute(() -> modelDAO.findAll());
        return ModelConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<ModelDTO> findModels(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForModel();

        Supplier<ListForPage<ModelDTO>> find = () -> {
            List<Model> repairs = modelDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) modelDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ModelDTO> list = ModelConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<ModelDTO> findModels(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForModel();

        Supplier<ListForPage<ModelDTO>> find = () -> {
            List<Model> repairs = modelDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) modelDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ModelDTO> list = ModelConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }


}