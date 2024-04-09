package it.academy.services.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dao.spare_parts_order.SparePartDAO;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.services.AdminService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.repair.RepairTypeConverter;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class AdminServiceImpl implements AdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private SparePartDAO sparePartDAO = new SparePartDAOImpl();

    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber) {

        List<EntityFilter> filters = getFiltersForRepairType();

        Supplier<ListForPage<RepairTypeDTO>> find = () -> {
            List<RepairType> repairs = repairTypeDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) repairTypeDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<RepairTypeDTO> list = RepairTypeConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForRepairType();

        Supplier<ListForPage<RepairTypeDTO>> find = () -> {
            List<RepairType> repairs = repairTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) repairTypeDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<RepairTypeDTO> list = RepairTypeConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public RepairTypeDTO findRepairType(long id) {
        RepairType repairType = transactionManger.execute(() -> repairTypeDAO.find(id));
        return RepairTypeConverter.convertToDTO(repairType);
    }

    @Override
    public void addRepairType(RepairTypeDTO repairType) {
        RepairType result = RepairTypeConverter.convertDTOToEntity(repairType);
        transactionManger.execute(() -> repairTypeDAO.create(result));
    }

    @Override
    public void updateRepairType(RepairTypeDTO repairType) {
        RepairType result = RepairTypeConverter.convertDTOToEntity(repairType);
        transactionManger.execute(() -> repairTypeDAO.update(result));
    }

    @Override
    public List<DeviceTypeDTO> findDeviceTypes() {
        List<DeviceType> repairs = transactionManger.execute(() -> deviceTypeDAO.findAll());
        return DeviceTypeConverter.convertListToDTO(repairs);
    }

    @Override
    public void addSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId) {
        SparePart sparePart = SparePartConverter.convertDTOToEntity(sparePartDTO);
        Supplier<SparePartDTO> create = () -> {
            sparePartDAO.create(sparePart);
            deviceTypesId.forEach(id -> {
                DeviceType deviceType = deviceTypeDAO.find(id);
                deviceType.addSparePart(sparePart);
                deviceTypeDAO.update(deviceType);
            });
            return SparePartConverter.convertToDTO(sparePart);
        };

        transactionManger.execute(create);
    }

    private List<EntityFilter> getFiltersForRepairType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER));
        filters.add(new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }

}
