package it.academy.services.impl;

import it.academy.dao.BrandDAO;
import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.ModelDAO;
import it.academy.dao.impl.BrandDAOImpl;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.ModelDAOImpl;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.ModelListDTO;
import it.academy.utils.enums.RoleEnum;
import it.academy.entities.Brand;
import it.academy.entities.DeviceType;
import it.academy.entities.Model;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.ModelService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.converters.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

import static it.academy.utils.Constants.*;

@Slf4j
public class ModelServiceImpl implements ModelService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public void createModel(ChangeModelDTO model) throws AccessDenied {
        changeModel(model, modelDAO::create);
        log.info(String.format(OBJECT_CREATED_PATTERN, model));
    }

    @Override
    public void updateModel(ChangeModelDTO model) throws AccessDenied {
        changeModel(model, modelDAO::update);
        log.info(String.format(OBJECT_UPDATED_PATTERN, model));
    }

    private void changeModel(ChangeModelDTO model, Consumer<Model> method) throws AccessDenied {
        ServiceHelper.checkCurrentAccount(model.getCurrentAccount());

        Model result = ModelConverter.convertToEntity(model);

        transactionManger.beginTransaction();
        List<Brand> brands = brandDAO.findAll();
        List<DeviceType> deviceTypes = deviceTypeDAO.findAll();

        if (brands.isEmpty()) {
            throw new BrandsNotFound();
        }

        if (deviceTypes.isEmpty()) {
            throw new DeviceTypesNotFound();
        }

        Brand brand = brandDAO.find(model.getBrandId());
        DeviceType deviceType = deviceTypeDAO.find(model.getDeviceTypeId());
        result.setBrand(brand);
        result.setType(deviceType);

        Model temp = modelDAO.getModel(result);
        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(MODEL_ALREADY_EXIST);
        }

        try {
            method.accept(result);
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), result));
            throw e;
        }

        transactionManger.commit();
    }

    @Override
    public ModelDTO findModel(long id) {
        return transactionManger.execute(() -> {
            ModelDTO result = ModelConverter.convertToDTO(modelDAO.find(id));
            try {
                List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
                List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findAll());
                result.setDeviceTypes(deviceTypes);
                result.setBrands(brands);
                return result;
            } catch (Exception e) {
                log.error(String.format(ERROR_PATTERN, e.getMessage(), result));
                throw e;
            }
        });
    }

    @Override
    public List<ModelDTO> findModels(AccountDTO accountDTO) {

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return ModelConverter.convertToDTOList(modelDAO.findActiveObjects(true));
        }

        List<Model> models = transactionManger.execute(modelDAO::findAll);
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