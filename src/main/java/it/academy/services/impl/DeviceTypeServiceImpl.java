package it.academy.services.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.utils.enums.RoleEnum;
import it.academy.entities.DeviceType;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.DeviceTypeService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;

import static it.academy.utils.Constants.*;

public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();

    @Override
    public void createDeviceType(DeviceTypeDTO deviceType) throws AccessDenied {
        ServiceHelper.checkCurrentAccount(deviceType.getCurrentAccount());

        DeviceType result = DeviceTypeConverter.convertToEntity(deviceType);
        transactionManger.beginTransaction();

        if (deviceTypeDAO.findByUniqueParameter(DEVICE_TYPE_NAME, deviceType.getName()) != null) {
            transactionManger.commit();
            throw new IllegalArgumentException(DEVICE_TYPE_ALREADY_EXIST);
        }

        deviceTypeDAO.create(result);

        transactionManger.commit();
    }

    @Override
    public void updateDeviceType(DeviceTypeDTO deviceType) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(deviceType.getCurrentAccount());

        DeviceType result = DeviceTypeConverter.convertToEntity(deviceType);
        transactionManger.beginTransaction();

        DeviceType temp = deviceTypeDAO.findByUniqueParameter(DEVICE_TYPE_NAME, deviceType.getName());

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(DEVICE_TYPE_ALREADY_EXIST);
        }

        deviceTypeDAO.update(result);

        transactionManger.commit();
    }

    @Override
    public DeviceTypeDTO findDeviceType(long id) {
        DeviceType deviceType = transactionManger.execute(() -> deviceTypeDAO.find(id));
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
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return ServiceHelper.getList(deviceTypeDAO,
                    () -> deviceTypeDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input), pageNumber,
                    DeviceTypeConverter::convertToDTOList,
                    FilterManager::getFiltersForDeviceType);
        }
        return ServiceHelper.getList(deviceTypeDAO,
                () -> deviceTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                DeviceTypeConverter::convertToDTOList,
                FilterManager::getFiltersForDeviceType);
    }

}
