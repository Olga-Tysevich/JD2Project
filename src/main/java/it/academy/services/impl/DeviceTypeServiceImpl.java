package it.academy.services.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.DeviceType;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.DeviceTypeService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);

    @Override
    public void createDeviceType(DeviceTypeDTO deviceType) throws AccessDenied {
        changeDeviceType(deviceType, deviceTypeDAO::create);
        log.info(String.format(OBJECT_CREATED_PATTERN, deviceType));
    }

    @Override
    public void updateDeviceType(DeviceTypeDTO deviceType) throws AccessDenied {
        changeDeviceType(deviceType, deviceTypeDAO::update);
        log.info(String.format(OBJECT_UPDATED_PATTERN, deviceType));
    }

    private void changeDeviceType(DeviceTypeDTO deviceType, Consumer<DeviceType> method) {

        ServiceHelper.checkCurrentAccount(deviceType.getCurrentAccount());

        DeviceType result = DeviceTypeConverter.convertToEntity(deviceType);
        transactionManger.beginTransaction();

        DeviceType temp = deviceTypeDAO.findByUniqueParameter(OBJECT_NAME, deviceType.getName());

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(DEVICE_TYPE_ALREADY_EXIST);
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
    public DeviceTypeDTO findDeviceType(long id) {
        DeviceType deviceType = transactionManger.execute(() -> {
            try {
                DeviceType result = deviceTypeDAO.find(id);
                log.info(String.format(OBJECT_FOUND_PATTERN, result));
                return result;
            } catch (Exception e) {
                log.error(String.format(ERROR_PATTERN, e.getMessage(), OBJECT_ID + id));
                throw e;
            }
        });
        return DeviceTypeConverter.convertToDTO(deviceType);
    }

    @Override
    public List<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO) {
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findActiveObjects(true));
        }

        List<DeviceType> deviceTypes = transactionManger.execute(deviceTypeDAO::findAll);
        return DeviceTypeConverter.convertToDTOList(deviceTypes);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber) {

        return findDeviceTypes(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber, String filter, String input) {

        Supplier<List<DeviceType>> find;
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            find = () -> deviceTypeDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            find = () -> deviceTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }
        return ServiceHelper.getList(deviceTypeDAO,
                find,
                pageNumber,
                DeviceTypeConverter::convertToDTOList,
                FilterManager::getFiltersForDeviceType);
    }

}
