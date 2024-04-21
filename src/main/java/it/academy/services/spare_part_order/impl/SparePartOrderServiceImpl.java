package it.academy.services.spare_part_order.impl;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_part.impl.SparePartOrderDAOImpl;
import it.academy.dto.spare_part.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.repair.Repair;
import it.academy.utils.enums.RepairStatus;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.utils.Builder;
import it.academy.utils.converters.spare_part.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.*;

public class SparePartOrderServiceImpl implements SparePartOrderService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final SparePartOrderDAO sparePartOrderDAO = new SparePartOrderDAOImpl(transactionManger);
    private final RepairDAO repairDAO = new RepairDAOImpl(transactionManger);


    @Override
    public void addSparePartOrder(SparePartOrderDTO partOrderDTO) {
        SparePartOrder order = SparePartOrderConverter.convertDTOToEntity(partOrderDTO);
        transactionManger.execute(() -> {
            Repair repair = repairDAO.find(partOrderDTO.getRepairId());
            repair.setStatus(RepairStatus.WAITING_FOR_SPARE_PARTS);
            order.setRepair(repair);
            sparePartOrderDAO.create(order);
            return repairDAO.update(repair);
        });
    }


    @Override
    public void changeSparePartOrder(ChangeSparePartOrderDTO partDTO) {
        transactionManger.execute(() -> {
            SparePartOrder order = sparePartOrderDAO.find(partDTO.getId());
            order.setDepartureDate(partDTO.getDepartureDate());
            order.setDeliveryDate(partDTO.getDeliveryDate());
            return sparePartOrderDAO.update(order);
        });
    }

    @Override
    public List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id) {
        Supplier<List<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> sparePart = sparePartOrderDAO.findSparePartOrdersByRepairId(id);
            return SparePartOrderConverter.convertListToDTO(sparePart);
        };
        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForSparePartOrder();

        Supplier<ListForPage<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> repairs = sparePartOrderDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForSparePartOrder();

        Supplier<ListForPage<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> repairs = sparePartOrderDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public SparePartOrderDTO findSparePartOrder(long id) {
        return transactionManger.execute(() ->
                SparePartOrderConverter.convertToDTO(sparePartOrderDAO.find(id)));
    }

    @Override
    public void removeSparePartOrder(long id) {
        transactionManger.execute(() -> sparePartOrderDAO.delete(id));
    }

}
