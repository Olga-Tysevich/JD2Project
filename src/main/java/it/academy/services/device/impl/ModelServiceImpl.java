package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.ChangeModelDTO;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.ModelForChangeDTO;
import it.academy.entities.Brand;
import it.academy.entities.DeviceType;
import it.academy.entities.Model;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.services.device.ModelService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ModelServiceImpl implements ModelService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public void createModel(ChangeModelDTO modelDTO) throws AccessDenied {
        Model model = ModelConverter.convertToEntity(modelDTO);
        Supplier<Model> create = () -> {
            Brand brand = brandDAO.find(modelDTO.getBrandId());
            DeviceType deviceType = deviceTypeDAO.find(modelDTO.getDeviceTypeId());
            model.setBrand(brand);
            model.setType(deviceType);
            checkIfExist(model);
            checkIfComponentsAdded();
            return modelDAO.create(model);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, model);
    }

    @Override
    public void updateModel(ChangeModelDTO modelDTO) throws AccessDenied {
        Model model = ModelConverter.convertToEntity(modelDTO);
        Supplier<Model> create = () -> {
            Brand brand = brandDAO.find(modelDTO.getBrandId());
            DeviceType deviceType = deviceTypeDAO.find(modelDTO.getDeviceTypeId());
            model.setBrand(brand);
            model.setType(deviceType);
            checkIfExist(model);
            return modelDAO.update(model);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, model);
    }

    @Override
    public ModelForChangeDTO getModelForm() {
        return transactionManger.execute(() -> {
            ModelDTO modelDTO = Builder.buildEmptyModel();
            ModelForChangeDTO modelForChangeDTO = findDataForForm();
            modelForChangeDTO.setModelDTO(modelDTO);
            log.info(LoggerConstants.OBJECT_FOUND_PATTERN, modelDTO);
            return modelForChangeDTO;
        });
    }

    @Override
    public ModelForChangeDTO getModelForm(long id) {
        return transactionManger.execute(() -> {
            Model model = modelDAO.find(id);
            if (model == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, Model.class);
                throw new ObjectNotFound(MODEL_NOT_FOUND);
            }
            ModelDTO modelDTO = ModelConverter.convertToDTO(model);
            ModelForChangeDTO modelForChangeDTO = findDataForForm();
            modelForChangeDTO.setModelDTO(modelDTO);
            log.info(LoggerConstants.OBJECT_FOUND_PATTERN, modelDTO);
            return modelForChangeDTO;
        });
    }

    private ModelForChangeDTO findDataForForm() {
        checkIfComponentsAdded();
        List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
        List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findAll());
        return ModelForChangeDTO.builder()
                .brands(brands)
                .deviceTypes(deviceTypes)
                .build();
    }

    @Override
    public List<ModelDTO> findModels() {
        List<Model> models = transactionManger.execute(modelDAO::findAll);
        if (models.isEmpty()) {
            log.warn(OBJECTS_NOT_FOUND_PATTERN, Model.class);
            throw new ObjectNotFound(MODELS_NOT_FOUND);
        }
        return ModelConverter.convertToDTOList(models);
    }

    @Override
    public ListForPage<ModelDTO> findModels(int pageNumber) {
        long numberOfEntries = modelDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> modelDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<ModelDTO> findModels(int pageNumber, String filter, String input)  {
        long numberOfEntries = modelDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> modelDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<ModelDTO> find(Supplier<List<Model>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<Model> models = ServiceHelper.getList(transactionManger, methodForSearch, Model.class);
        List<EntityFilter> filters = FilterManager.getFiltersForModel();
        List<ModelDTO> modelsDTO = ModelConverter.convertToDTOList(models);
        return Builder.buildListForPage(modelsDTO, pageNumber, maxPageNumber, filters);
    }

    private void checkIfComponentsAdded() {
        if (brandDAO.getNumberOfEntries() == 0 || deviceTypeDAO.getNumberOfEntries() == 0) {
            log.warn(LoggerConstants.OBJECTS_NOT_FOUND_PATTERN, Brand.class);
            throw new BrandsNotFound();
        }
    }

    private void checkIfExist(Model model) {
        if (modelDAO.checkIfExist(model)) {
            log.warn(OBJECT_ALREADY_EXIST, model);
            throw new ObjectAlreadyExist(MODEL_ALREADY_EXIST);
        }
    }


}