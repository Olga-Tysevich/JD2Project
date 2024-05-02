package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelForChangeDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.device.DeviceType;
import it.academy.entities.device.Model;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.utils.Builder;
import it.academy.utils.PageCounter;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.converters.impl.DeviceTypeConverter;
import it.academy.utils.converters.impl.ModelConverter;
import it.academy.utils.dao.TransactionManger;
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
    private final DeviceTypeConverter deviceTypeConverter = new DeviceTypeConverter();
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);
    private final BrandConverter brandConverter = new BrandConverter();
    private final ModelConverter modelConverter = new ModelConverter();
    private ServiceHelper<Model, ModelDTO> modelHelper =
            new ServiceHelper<>(modelDAO, Model.class, modelConverter, transactionManger);

    @Override
    public void create(ChangeModelDTO modelDTO) throws AccessDenied {
        Model model = modelConverter.convertToEntity(modelDTO);
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
    public void update(ChangeModelDTO modelDTO) throws AccessDenied {
        Model model = modelConverter.convertToEntity(modelDTO);
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
    public void delete(long id) {
        modelHelper.delete(id);
    }

    @Override
    public ModelForChangeDTO getForm() {
        return transactionManger.execute(() -> {
            ModelDTO modelDTO = Builder.buildEmptyModel();
            ModelForChangeDTO modelForChangeDTO = findDataForForm();
            modelForChangeDTO.setModelDTO(modelDTO);
            log.info(LoggerConstants.OBJECT_FOUND_PATTERN, modelDTO);
            return modelForChangeDTO;
        });
    }

    @Override
    public ModelForChangeDTO getForm(long id) {
        return transactionManger.execute(() -> {
            Model model = modelDAO.find(id);
            if (model == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, Model.class);
                throw new ObjectNotFound(MODEL_NOT_FOUND);
            }
            ModelDTO modelDTO = modelConverter.convertToDTO(model);
            ModelForChangeDTO modelForChangeDTO = findDataForForm();
            modelForChangeDTO.setModelDTO(modelDTO);
            log.info(LoggerConstants.OBJECT_FOUND_PATTERN, modelDTO);
            return modelForChangeDTO;
        });
    }

    private ModelForChangeDTO findDataForForm() {
        checkIfComponentsAdded();
        List<DeviceType> deviceTypeList = transactionManger.execute(deviceTypeDAO::findAll);
        List<DeviceTypeDTO> deviceTypes = deviceTypeConverter.convertToDTOList(deviceTypeList);
        List<BrandDTO> brands = brandConverter.convertToDTOList(brandDAO.findAll());
        return ModelForChangeDTO.builder()
                .brands(brands)
                .deviceTypes(deviceTypes)
                .build();
    }

    @Override
    public List<ModelDTO> findAll() {
        return modelHelper.findAll();
    }


    @Override
    public TablePage2<ModelDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<ModelDTO>> find = () -> {
            long numberOfEntries = modelDAO.getNumberOfEntries();
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Model> models = modelDAO.findForPage(pageNumberForSearch, LIST_SIZE);
            List<ModelDTO> listDTO = modelConverter.convertToDTOList(models);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<ModelDTO> findByName(int pageNumber, String input) {
        Supplier<TablePage2<ModelDTO>> find = () -> {
            long numberOfEntries = modelDAO.getNumberOfEntriesByFilter(OBJECT_NAME, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Model> models = modelDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, OBJECT_NAME, input);
            List<ModelDTO> listDTO = modelConverter.convertToDTOList(models);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<ModelDTO> findByComponentName(int pageNumber, String filter, String input) {
        Supplier<TablePage2<ModelDTO>> find = () -> {
            long numberOfEntries = modelDAO.getNumberOfEntriesByComponent(filter, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Model> models = modelDAO.findByComponent(filter, input, pageNumberForSearch, LIST_SIZE);
            List<ModelDTO> listDTO = modelConverter.convertToDTOList(models);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkIfComponentsAdded() {
        if (brandDAO.getNumberOfEntries() == 0) {
            log.warn(LoggerConstants.OBJECTS_NOT_FOUND_PATTERN, Brand.class);
            throw new BrandsNotFound();
        } else if (deviceTypeDAO.getNumberOfEntries() == 0) {
            log.warn(LoggerConstants.OBJECTS_NOT_FOUND_PATTERN, DeviceType.class);
            throw new DeviceTypesNotFound();
        }
    }

    private void checkIfExist(Model model) {
        if (modelDAO.checkIfExist(model)) {
            log.warn(OBJECT_ALREADY_EXIST, model);
            throw new ObjectAlreadyExist(MODEL_ALREADY_EXIST);
        }
    }


}