package it.academy.services.impl;

import it.academy.dao.RepairWorkshopDAO;
import it.academy.dao.impl.RepairWorkshopDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.ServiceCenterService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class ServiceCenterServiceImpl implements ServiceCenterService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairWorkshopDAO repairWorkshopDAO = new RepairWorkshopDAOImpl();

    @Override
    public void addRepairWorkshop(RepairWorkshopDTO repairWorkshop) {
        RepairWorkshop result = RepairWorkshopConverter.convertDTOToEntity(repairWorkshop);
        transactionManger.execute(() -> repairWorkshopDAO.create(result));
    }

    @Override
    public void updateRepairWorkshop(RepairWorkshopDTO repairWorkshop) {
        RepairWorkshop result = RepairWorkshopConverter.convertDTOToEntity(repairWorkshop);
        transactionManger.execute(() -> repairWorkshopDAO.update(result));
    }

    @Override
    public RepairWorkshopDTO findRepairWorkshop(long id) {
        RepairWorkshop result = transactionManger.execute(() -> repairWorkshopDAO.find(id));
        return RepairWorkshopConverter.convertToDTO(result);
    }

    @Override
    public List<RepairWorkshopDTO> findRepairWorkshops() {
        List<RepairWorkshop> repairs = transactionManger.execute(() -> repairWorkshopDAO.findAll());
        return RepairWorkshopConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<RepairWorkshopDTO> findRepairWorkshops(int pageNumber) {
        List<EntityFilter> filters = getFiltersForRepairWorkshop();

        Supplier<ListForPage<RepairWorkshopDTO>> find = () -> {
            List<RepairWorkshop> repairs = repairWorkshopDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) repairWorkshopDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<RepairWorkshopDTO> list = RepairWorkshopConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<RepairWorkshopDTO> findRepairWorkshops(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForRepairWorkshop();

        Supplier<ListForPage<RepairWorkshopDTO>> find = () -> {
            List<RepairWorkshop> repairs = repairWorkshopDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) repairWorkshopDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<RepairWorkshopDTO> list = RepairWorkshopConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    private List<EntityFilter> getFiltersForRepairWorkshop() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        filters.add(new EntityFilter(IS_ACTIVE, IS_BLOCKED));
        return filters;
    }


}
