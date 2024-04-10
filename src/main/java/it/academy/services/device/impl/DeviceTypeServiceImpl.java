package it.academy.services.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.services.device.DeviceTypeService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.Constants.*;

public class DeviceTypeServiceImpl implements DeviceTypeService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();

    @Override
    public void addDeviceType(DeviceTypeDTO deviceType) {
        DeviceType result = DeviceTypeConverter.convertDTOToEntity(deviceType);
        transactionManger.execute(() -> deviceTypeDAO.create(result));
    }

    @Override
    public void updateDeviceType(DeviceTypeDTO deviceType) {
        DeviceType result = DeviceTypeConverter.convertDTOToEntity(deviceType);
        transactionManger.execute(() -> deviceTypeDAO.update(result));
    }

    @Override
    public DeviceTypeDTO findDeviceType(long id) {
        DeviceType deviceType = transactionManger.execute(() -> deviceTypeDAO.find(id));
        return DeviceTypeConverter.convertToDTO(deviceType);
    }

    @Override
    public List<DeviceTypeDTO> findDeviceTypes() {
        List<DeviceType> repairs = transactionManger.execute(() -> deviceTypeDAO.findAll());
        return DeviceTypeConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber) {
        List<EntityFilter> filters = getFiltersForDeviceType();

        Supplier<ListForPage<DeviceTypeDTO>> find = () -> {
            List<DeviceType> repairs = deviceTypeDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) deviceTypeDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<DeviceTypeDTO> list = DeviceTypeConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForDeviceType();

        Supplier<ListForPage<DeviceTypeDTO>> find = () -> {
            List<DeviceType> repairs = deviceTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) deviceTypeDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<DeviceTypeDTO> list = DeviceTypeConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    private List<EntityFilter> getFiltersForDeviceType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        filters.add(new EntityFilter(IS_ACTIVE, IS_BLOCKED));
        return filters;
    }

}
