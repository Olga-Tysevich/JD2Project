package it.academy.services.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.device.DeviceType;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.DeviceTypeService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;

@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);

    @Override
    public void create(DeviceTypeDTO deviceTypeDTO) {
        DeviceType deviceType = DeviceTypeConverter.convertToEntity(deviceTypeDTO);
        checkDeviceTypeName(deviceType.getName());
        Supplier<DeviceType> create = () -> {
            checkDeviceType(ID_FOR_CHECK, deviceType.getName());
            DeviceType result = deviceTypeDAO.create(deviceType);
            log.info(OBJECT_CREATED_PATTERN, result);
            return result;
        };
        transactionManger.execute(create);
    }

    @Override
    public void update(DeviceTypeDTO deviceTypeDTO) {
        DeviceType deviceType = DeviceTypeConverter.convertToEntity(deviceTypeDTO);
        checkDeviceTypeName(deviceType.getName());
        Supplier<DeviceType> create = () -> {
            checkDeviceType(deviceTypeDTO.getId(), deviceType.getName());
            DeviceType result = deviceTypeDAO.update(deviceType);
            log.info(OBJECT_CREATED_PATTERN, result);
            return result;
        };
        transactionManger.execute(create);
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> deviceTypeDAO.delete(id));
    }

    @Override
    public DeviceTypeDTO find(long id) {
        DeviceType deviceType = transactionManger.execute(() -> deviceTypeDAO.find(id));
        return DeviceTypeConverter.convertToDTO(deviceType);
    }

    @Override
    public List<DeviceTypeDTO> findAll() {
        List<DeviceType> deviceTypes = transactionManger.execute(deviceTypeDAO::findAll);
        return DeviceTypeConverter.convertToDTOList(deviceTypes);
    }

    @Override
    public TablePage<DeviceTypeDTO> findForPage(int pageNumber, Map<String, String> userInput) {
        Supplier<TablePage<DeviceTypeDTO>> find = () -> {
            long numberOfEntries = deviceTypeDAO.getNumberOfEntries(userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<DeviceType> deviceTypes = deviceTypeDAO.findAllByPageAndFilter(pageNumberForSearch, LIST_SIZE, userInput);
            List<DeviceTypeDTO> dtoList = DeviceTypeConverter.convertToDTOList(deviceTypes);
            return new TablePage<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkDeviceTypeName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException(NAME_IS_EMPTY);
        }
    }

    private void checkDeviceType(long id, String name) {
        boolean isExist = deviceTypeDAO.checkIfExist(id, name);
        if (isExist) {
            throw new ObjectAlreadyExist(DEVICE_TYPE_ALREADY_EXIST);
        }
    }
}
