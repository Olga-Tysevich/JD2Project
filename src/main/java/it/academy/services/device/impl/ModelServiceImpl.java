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
import it.academy.dto.device.req.ChangeModelDTO;
import it.academy.dto.device.resp.ModelDTO;
import it.academy.dto.device.resp.ModelListDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
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
import java.util.function.Consumer;

import static it.academy.utils.Constants.LIST_SIZE;
import static it.academy.utils.Constants.MODEL_ALREADY_EXIST;

public class ModelServiceImpl implements ModelService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public void createModel(ChangeModelDTO model) throws AccessDenied {
        changeModel(model, modelDAO::create);
    }

    @Override
    public void updateModel(ChangeModelDTO model) throws AccessDenied {
        changeModel(model, modelDAO::update);
    }

    private void changeModel(ChangeModelDTO model, Consumer<Model> method) throws AccessDenied {
        ServiceHelper.checkCurrentAccount(model.getCurrentAccount());

        Model result = ModelConverter.convertToEntity(model);
        transactionManger.beginTransaction();

        Brand brand = brandDAO.find(model.getBrandId());
        DeviceType deviceType = deviceTypeDAO.find(model.getDeviceTypeId());
        result.setBrand(brand);
        result.setType(deviceType);

        Model temp = modelDAO.getModel(result);
        System.out.println("change model " + model);
        System.out.println("change model temp " + temp);
        System.out.println("change model temp equals" + (temp != null && !temp.getId().equals(result.getId())));
        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
        }

        method.accept(result);

        transactionManger.commit();
    }

    @Override
    public ModelDTO findModel(long id) {
        return transactionManger.execute(() -> {
            ModelDTO result = ModelConverter.convertToDTO(modelDAO.find(id));
            List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
            List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findAll());
            result.setDeviceTypes(deviceTypes);
            result.setBrands(brands);
            return result;
        });
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