package it.academy.services.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.DeviceType;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.device.DeviceTypeService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);

    @Override
    public void createDeviceType(DeviceTypeDTO deviceTypeDTO) {
        DeviceType deviceType = DeviceTypeConverter.convertToEntity(deviceTypeDTO);
        Supplier<DeviceType> create = () -> {
            checkIfExist(deviceType);
            return deviceTypeDAO.create(deviceType);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, deviceType);
    }

    @Override
    public void updateDeviceType(DeviceTypeDTO deviceTypeDTO) {
        DeviceType deviceType = DeviceTypeConverter.convertToEntity(deviceTypeDTO);
        Supplier<DeviceType> create = () -> {
            checkIfExist(deviceType);
            return deviceTypeDAO.update(deviceType);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_UPDATED_PATTERN, deviceType);
    }

    @Override
    public DeviceTypeDTO findDeviceType(long id) {
        DeviceType deviceType = transactionManger.execute(() -> {
            return deviceTypeDAO.find(id);
        });
        if (deviceType == null) {
            log.warn(OBJECT_NOT_FOUND_PATTERN, id, DeviceType.class);
            throw new ObjectNotFound(DEVICE_TYPE_NOT_FOUND);
        }
        return DeviceTypeConverter.convertToDTO(deviceType);
    }

    @Override
    public List<DeviceTypeDTO> findDeviceTypes() {
        List<DeviceType> deviceTypes = transactionManger.execute(deviceTypeDAO::findAll);
        if (deviceTypes.isEmpty()) {
            log.warn(OBJECTS_NOT_FOUND_PATTERN, DeviceType.class);
            throw new ObjectNotFound(DEVICE_TYPE_NOT_FOUND);
        }
        return DeviceTypeConverter.convertToDTOList(deviceTypes);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber) {
        long numberOfEntries = deviceTypeDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> deviceTypeDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input) {
        long numberOfEntries = deviceTypeDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> deviceTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<DeviceTypeDTO> find(Supplier<List<DeviceType>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<DeviceType> deviceTypes = ServiceHelper.getList(transactionManger, methodForSearch, DeviceType.class);
        List<EntityFilter> filters = FilterManager.getFiltersForDeviceType();
        List<DeviceTypeDTO> listDTO = DeviceTypeConverter.convertToDTOList(deviceTypes);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    private void checkIfExist(DeviceType deviceType) {
        long deviceTypeId = deviceType.getId() != null ? deviceType.getId() : 0L;
        if (deviceTypeDAO.checkIfComponentExist(deviceTypeId, deviceType.getName())) {
            log.warn(OBJECT_ALREADY_EXIST, deviceType);
            transactionManger.rollback();
            throw new ObjectAlreadyExist(DEVICE_TYPE_ALREADY_EXIST);
        }
    }

}
