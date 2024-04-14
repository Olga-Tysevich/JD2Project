package it.academy.services.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.DeviceType;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.device.DeviceTypeService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import static it.academy.utils.Constants.*;

public class DeviceTypeServiceImpl implements DeviceTypeService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();

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

        List<DeviceType> deviceTypes = transactionManger.execute(() -> deviceTypeDAO.findAll());
        return DeviceTypeConverter.convertToDTOList(deviceTypes);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber) {

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getDeviceTypeList(() -> deviceTypeDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE), pageNumber,
                    DeviceTypeConverter::convertToDTOList);
        }

        return getDeviceTypeList(() -> deviceTypeDAO.findForPage(pageNumber, LIST_SIZE), pageNumber,
                DeviceTypeConverter::convertToDTOList);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(AccountDTO accountDTO, int pageNumber, String filter, String input) {
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getDeviceTypeList(() -> deviceTypeDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input), pageNumber,
                    DeviceTypeConverter::convertToDTOList);
        }
        return getDeviceTypeList(() -> deviceTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                DeviceTypeConverter::convertToDTOList);
    }

    private ListForPage<DeviceTypeDTO> getDeviceTypeList(Supplier<List<DeviceType>> method, int pageNumber,
                                                         Function<List<DeviceType>, List<DeviceTypeDTO>> converter) {
        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();

        Supplier<ListForPage<DeviceTypeDTO>> find = () -> {
            List<DeviceType> deviceTypes = method.get();
            int maxPageNumber = (int) Math.ceil(((double) deviceTypeDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<DeviceTypeDTO> list = converter.apply(deviceTypes);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

}
