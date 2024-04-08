package it.academy.services.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_parts_order.SparePartDAO;
import it.academy.dao.spare_parts_order.SparePartsOrderDAO;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.dao.spare_parts_order.impl.SparePartsOrderDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import it.academy.services.SparePartService;
import it.academy.utils.Builder;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.converters.spare_parst.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;

public class SparePartServiceImpl implements SparePartService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private SparePartDAO sparePartDAO = new SparePartDAOImpl();
    private SparePartsOrderDAO sparePartsOrderDAO = new SparePartsOrderDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private RepairDAO repairDAO = new RepairDAOImpl();

    @Override
    public void addSparePart(SparePartDTO partDTO) {
        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
        transactionManger.execute(() -> {
            SparePart sparePartAfterSave = sparePartDAO.create(sparePart);
            partDTO.getRelatedDeviceTypes().forEach(dt -> {
                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
                deviceType.addSpareParts(sparePartAfterSave);
                deviceTypeDAO.update(deviceType);
            });
            return sparePartAfterSave;
        });
    }

    @Override
    public void changeSparePart(SparePartDTO partDTO) {
        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
        transactionManger.execute(() -> {
            SparePart sparePartAfterSave = sparePartDAO.update(sparePart);
            partDTO.getRelatedDeviceTypes().forEach(dt -> {
                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
                deviceType.addSpareParts(sparePartAfterSave);
                deviceTypeDAO.update(deviceType);
            });
            return sparePart;
        });
    }

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
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber) {
        Supplier<ListForPage<SparePartDTO>> find = () -> {
            List<SparePart> repairs = sparePartDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) sparePartDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<SparePartDTO> list = SparePartConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
        };

        return transactionManger.execute(find);
    }

    @Override
    public void addSparePartOrder(SparePartOrderDTO partOrderDTO) {
        SparePartsOrder order = SparePartOrderConverter.convertDTOToEntity(partOrderDTO);
        transactionManger.execute(() -> {
            Repair repair = repairDAO.find(partOrderDTO.getRepairId());
            repair.setStatus(RepairStatus.WAITING_FOR_SPARE_PARTS);
            order.setRepair(repair);
            sparePartsOrderDAO.create(order);
            return repairDAO.update(repair);
        });
    }

    @Override
    public List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id) {
        Supplier<List<SparePartOrderDTO>> find = () -> {
            List<SparePartsOrder> sparePart = sparePartsOrderDAO.findSparePartOrdersByRepairId(id);
            return SparePartOrderConverter.convertListToDTO(sparePart);
        };
        return transactionManger.execute(find);
    }

    @Override
    public List<SparePartDTO> findSparePartsByDeviceTypeId(long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            List<SparePart> sparePart = sparePartDAO.findByDeviceTypeId(id);
            return SparePartConverter.convertListToDTO(sparePart);
        };
        return transactionManger.execute(find);
    }

    @Override
    public SparePartOrderDTO findSparePartOrder(long id) {
        return transactionManger.execute(() ->
                SparePartOrderConverter.convertToDTO(sparePartsOrderDAO.find(id)));
    }

    @Override
    public void changeSparePartOrder(SparePartOrderDTO partDTO) {
        SparePartsOrder order = SparePartOrderConverter.convertDTOToEntity(partDTO);
        transactionManger.execute(() -> {
            Repair repair = repairDAO.find(partDTO.getRepairId());
            repair.setStatus(RepairStatus.WAITING_FOR_SPARE_PARTS);
            order.setRepair(repair);
            sparePartsOrderDAO.update(order);
            return repairDAO.update(repair);
        });
    }
}
