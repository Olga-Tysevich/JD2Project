package it.academy.services.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.device.ModelDTO;
import it.academy.entities.device.components.Model;
import it.academy.services.ModelService;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.List;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public ModelDTO findModel(long id) {
        Model model = transactionManger.execute(() -> modelDAO.find(id));
        ModelDTO modelDTO = ModelConverter.convertToDTO(model);
        transactionManger.closeManager();
        return modelDTO;
    }

    @Override
    public List<ModelDTO> findModelsByBrandId(long brandId) {
        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
        List<ModelDTO> modelDTOList = ModelConverter.convertListToDTO(models);
        transactionManger.closeManager();
        return modelDTOList;
    }
}
