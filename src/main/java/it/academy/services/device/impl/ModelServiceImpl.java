package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.req.ModelDTO;
import it.academy.dto.device.resp.ModelListDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.Model;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public void createModel(ModelDTO model) throws AccessDenied {
        ServiceHelper.checkCurrentAccount(model.getCurrentAccount());

        Model result = ModelConverter.convertToEntity(model);
        transactionManger.beginTransaction();

        if (modelDAO.getModel(result) != null) {
            transactionManger.commit();
            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
        }

        modelDAO.create(result);

        transactionManger.commit();
    }

    @Override
    public void updateModel(ModelDTO model) throws AccessDenied {
        ServiceHelper.checkCurrentAccount(model.getCurrentAccount());

        Model result = ModelConverter.convertToEntity(model);
        transactionManger.beginTransaction();

        Model temp = modelDAO.getModel(result);

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
        }

        modelDAO.update(result);

        transactionManger.commit();
    }

    @Override
    public ModelDTO findModel(long id) {
        Model deviceType = transactionManger.execute(() -> modelDAO.find(id));
        return ModelConverter.convertToDTO(deviceType);
    }

    @Override
    public List<ModelDTO> findModels(AccountDTO accountDTO) {

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return ModelConverter.convertToDTOList(modelDAO.findActiveObjects(true));
        }

        List<Model> models = transactionManger.execute(() -> modelDAO.findAll());
        return ModelConverter.convertToDTOList(models);
    }

    @Override
    public ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber)
            throws DeviceTypesNotFound, BrandsNotFound {
        return getModelList(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<ModelListDTO> findModels(AccountDTO accountDTO, int pageNumber, String filter, String input)
            throws DeviceTypesNotFound, BrandsNotFound {
        return getModelList(accountDTO, pageNumber, filter, input);
    }

    private ListForPage<ModelListDTO> getModelList(AccountDTO accountDTO, int pageNumber, String filter, String input)
            throws BrandsNotFound, DeviceTypesNotFound {
        List<DeviceTypeDTO> deviceTypes;
        List<BrandDTO> brands;
        List<ModelDTO> models;

        transactionManger.beginTransaction();
        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
            brands = BrandConverter.convertToDTOList(brandDAO.findAll());
            models = ModelConverter.convertToDTOList(modelDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input));
        } else {
            deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input));
            brands = BrandConverter.convertToDTOList(brandDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input));
            models = ModelConverter.convertToDTOList(modelDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input));
        }

        if (brands.isEmpty()) {
            throw new BrandsNotFound();
        }

        if (deviceTypes.isEmpty()) {
            throw new DeviceTypesNotFound();
        }

        transactionManger.commit();
        int maxPageNumber = (int) Math.ceil(((double) models.size()) / LIST_SIZE);
        ModelListDTO modelListDTO = ModelListDTO.builder()
                .brands(brands)
                .deviceTypes(deviceTypes)
                .models(models)
                .build();

        return Builder.buildListForPage(List.of(modelListDTO), pageNumber, maxPageNumber, FilterManager.getFiltersForModel());
    }

}