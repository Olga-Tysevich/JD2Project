package it.academy.services.spare_part.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.spare_parts_order.SparePartDAO;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.services.spare_part.SparePartService;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class SparePartServiceImpl implements SparePartService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private SparePartDAO sparePartDAO = new SparePartDAOImpl();


    @Override
    public List<SparePartDTO> findSparePartsByDeviceTypeId(long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            List<SparePart> sparePart = sparePartDAO.findByDeviceTypeId(id);
            return SparePartConverter.convertListToDTO(sparePart);
        };
        return transactionManger.execute(find);
    }

//    @Override
//    public void addSparePart(SparePartDTO partDTO) {
//        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
//        transactionManger.execute(() -> {
//            SparePart sparePartAfterSave = sparePartDAO.create(sparePart);
//            partDTO.getRelatedDeviceTypes().forEach(dt -> {
//                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
//                deviceType.addSparePart(sparePartAfterSave);
//                deviceTypeDAO.update(deviceType);
//            });
//            return sparePartAfterSave;
//        });
//    }

//    @Override
//    public void changeSparePart(SparePartDTO partDTO) {
//        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
//        transactionManger.execute(() -> {
//            SparePart sparePartAfterSave = sparePartDAO.update(sparePart);
//            partDTO.getRelatedDeviceTypes().forEach(dt -> {
//                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
//                deviceType.addSparePart(sparePartAfterSave);
//                deviceTypeDAO.update(deviceType);
//            });
//            return sparePart;
//        });
//    }

    @Override
    public SparePartDTO findSparePart(long id) {
        Supplier<SparePartDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            SparePartDTO result = SparePartConverter.convertToDTO(sparePart);
            List<DeviceType> types = deviceTypeDAO.findAll();
            List<DeviceTypeDTO> deviceTypeDTOList = DeviceTypeConverter.convertListToDTO(types);
            result.setAllDeviceTypes(deviceTypeDTOList);
            return result;
        };
        return transactionManger.execute(find);
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

    @Override
    public void updateSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId) {
        SparePart sparePart = SparePartConverter.convertDTOToEntity(sparePartDTO);
        Supplier<SparePartDTO> update = () -> {
            removeDeviceTypeFromSparePart(sparePart, deviceTypesId);
            addDeviceTypeToSparePart(sparePart, deviceTypesId);
            return SparePartConverter.convertToDTO(sparePart);
        };

        transactionManger.execute(update);
    }

    @Override
    public void deleteSparePart(long id) {
        transactionManger.execute(() -> {
            SparePart sparePart = sparePartDAO.find(id);
            List<DeviceType> deviceTypes = deviceTypeDAO.findBySparePartId(sparePart.getId());
            deviceTypes.forEach(dt -> dt.removeSparePart(sparePart));
            return sparePartDAO.delete(id);
        });
    }

    @Override
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber) {
        return null;
    }

    @Override
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input) {
        return null;
    }

    private void addDeviceTypeToSparePart(SparePart sparePart, List<Long> deviceTypesId) {
        deviceTypesId.forEach(id -> {
            DeviceType deviceType = deviceTypeDAO.find(id);
            deviceType.addSparePart(sparePart);
            deviceTypeDAO.update(deviceType);
        });
    }

    private void removeDeviceTypeFromSparePart(SparePart sparePart, List<Long> deviceTypesId) {
        deviceTypeDAO.findBySparePartId(sparePart.getId())
                .stream()
                .filter(dt -> !deviceTypesId.contains(dt.getId()))
                .forEach(dt -> {
                    dt.removeSparePart(sparePart);
                    deviceTypesId.remove(dt.getId());
                    deviceTypeDAO.update(dt);
                });
    }

    private List<EntityFilter> getFilters() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(DEVICE_TYPES, DEVICE_TYPE_NAME_DESCRIPTION));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_LEVEL_FILTER));
        return filters;
    }
}
