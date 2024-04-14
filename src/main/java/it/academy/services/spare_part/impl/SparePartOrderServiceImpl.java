package it.academy.services.spare_part.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.RepairDAO;
import it.academy.dao.SparePartDAO;
import it.academy.dao.SparePartsOrderDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.RepairDAOImpl;
import it.academy.dao.impl.SparePartDAOImpl;
import it.academy.dao.impl.SparePartsOrderDAOImpl;
import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.repair.spare_parts.SparePartOrderDTO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import it.academy.services.spare_part.SparePartOrderService;
import it.academy.utils.Builder;
import it.academy.utils.converters.spare_parst.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class SparePartOrderServiceImpl implements SparePartOrderService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl();
    private final SparePartsOrderDAO sparePartsOrderDAO = new SparePartsOrderDAOImpl();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private final RepairDAO repairDAO = new RepairDAOImpl();


    @Override
    public ListForPage<ChangeSparePartDTO> findSpareParts(int pageNumber) {
        List<EntityFilter> filters = getFiltersForSparePart();

        Supplier<ListForPage<ChangeSparePartDTO>> find = () -> {
            List<SparePart> repairs = sparePartDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) sparePartDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
//            List<SparePartDTO> list = SparePartConverter.convertToDTOList(repairs);
//            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
            return null;
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
    public ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber) {
        List<EntityFilter> filters = getFiltersForSparePartOrder();

        Supplier<ListForPage<SparePartOrderDTO>> find = () -> {
            List<SparePartsOrder> repairs = sparePartsOrderDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) sparePartsOrderDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForSparePartOrder();

        Supplier<ListForPage<SparePartOrderDTO>> find = () -> {
            List<SparePartsOrder> repairs = sparePartsOrderDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) sparePartsOrderDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
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
            order.setRepair(repair);
            sparePartsOrderDAO.update(order);
            return repairDAO.update(repair);
        });
    }

    @Override
    public void removeSparePartOrder(long id) {
        transactionManger.execute(() -> sparePartsOrderDAO.delete(id));
    }

    @Override
    public ListForPage<ChangeSparePartDTO> findSpareParts(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForSparePart();

        Supplier<ListForPage<ChangeSparePartDTO>> find = () -> {
            List<SparePart> repairs = sparePartDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) sparePartDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
//            List<SparePartDTO> list = SparePartConverter.convertToDTOList(repairs);
//            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
            return null;
        };

        return transactionManger.execute(find);
    }

    private List<EntityFilter> getFiltersForSparePartOrder() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(ORDER_DATE_PARAMETER, ORDER_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DEPARTURE_DATE_PARAMETER, ORDER_DEPARTURE_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DELIVERY_DATE_PARAMETER, ORDER_DELIVERY_DATE_DESCRIPTION));
        return filters;
    }


    private List<EntityFilter> getFiltersForSparePart() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, SPARE_PART_NAME_DESCRIPTION));
        filters.add(new EntityFilter(DEPARTURE_DATE_PARAMETER, DEVICE_TYPE_NAME_DESCRIPTION));
        return filters;
    }


}
