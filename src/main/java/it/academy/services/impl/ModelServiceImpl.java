package it.academy.services.impl;

import it.academy.dao.ModelDAO;
import it.academy.dao.impl.ModelDAOImpl;
import it.academy.dto.ModelDTO;
import it.academy.entities.device.components.Model;
import it.academy.services.ModelService;
import it.academy.utils.converters.ModelConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.List;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public ModelDTO findModel(long id) {
        return null;
    }

    @Override
    public List<ModelDTO> findModelsByBrandId(long brandId) {
        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
        List<ModelDTO> resp = ModelConverter.convertListToDTO(models);
        transactionManger.closeManager();
        return resp;
    }
}
