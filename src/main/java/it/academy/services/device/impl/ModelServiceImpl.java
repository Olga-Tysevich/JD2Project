package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.device.ModelFormDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.device.DeviceType;
import it.academy.entities.device.Model;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.device.ModelService;
import it.academy.utils.PageCounter;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.converters.ModelConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ModelServiceImpl implements ModelService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public ModelDTO create(ModelDTO modelDTO) {
        checkName(modelDTO.getName());
        Model model = ModelConverter.convertToEntity(modelDTO);
        Supplier<Model> create = () -> {
            checkIfComponentsAdded();
            Brand brand = brandDAO.find(modelDTO.getBrandId());
            DeviceType deviceType = deviceTypeDAO.find(modelDTO.getDeviceTypeId());
            model.setBrand(brand);
            model.setType(deviceType);
            checkIfExist(model);
            Model created = modelDAO.create(model);
            log.info(LoggerConstants.OBJECT_CREATED_PATTERN, created);
            return created;
        };
        transactionManger.execute(create);
        return modelDTO;
    }

    @Override
    public ModelDTO update(ModelDTO modelDTO) {
        checkName(modelDTO.getName());
        Model model = ModelConverter.convertToEntity(modelDTO);
        Supplier<Model> create = () -> {
            Brand brand = brandDAO.find(modelDTO.getBrandId());
            DeviceType deviceType = deviceTypeDAO.find(modelDTO.getDeviceTypeId());
            model.setBrand(brand);
            model.setType(deviceType);
            checkIfExist(model);
            Model updated = modelDAO.update(model);
            log.info(LoggerConstants.OBJECT_CREATED_PATTERN, updated);
            return updated;
        };
        transactionManger.execute(create);
        return modelDTO;
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> modelDAO.delete(id));
        log.info(OBJECT_DELETED_PATTERN, id, Model.class);
    }

    @Override
    public ModelFormDTO getForm() {
        List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
        List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findAll());
        return new ModelFormDTO(deviceTypes, brands, StringUtils.EMPTY);
    }

    @Override
    public ModelDTO find(long id) {
        Model result = transactionManger.execute(() -> modelDAO.find(id));
        return ModelConverter.convertToDTO(result);
    }

    @Override
    public List<ModelDTO> findAll() {
        List<Model> models = transactionManger.execute(modelDAO::findAll);
        List<ModelDTO> result = ModelConverter.convertToDTOList(models);
        return result.stream().sorted(
                Comparator.comparing(ModelDTO::getDeviceTypeName)
                        .thenComparing(ModelDTO::getBrandName)
                        .thenComparing(ModelDTO::getName)
        ).collect(Collectors.toList());
    }

    @Override
    public TablePage2<ModelDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<ModelDTO>> find = () -> {
            long numberOfEntries = modelDAO.getNumberOfEntries();
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Model> models = modelDAO.findForPage(pageNumberForSearch, LIST_SIZE);
            List<ModelDTO> listDTO = ModelConverter.convertToDTOList(models);
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
            List<ModelDTO> listDTO = ModelConverter.convertToDTOList(models);
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
            List<ModelDTO> listDTO = ModelConverter.convertToDTOList(models);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException(NAME_IS_EMPTY);
        }
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