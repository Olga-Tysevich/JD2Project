package it.academy.services.impl;

import it.academy.dao.RepairTypeDAO;
import it.academy.dao.impl.RepairTypeDAOImpl;
import it.academy.dto.resp.RepairTypeDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.RepairType;
import it.academy.services.RepairTypeService;
import it.academy.utils.Builder;
import it.academy.utils.converters.RepairTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;

public class RepairTypeServiceImpl implements RepairTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);


    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber) {

        List<EntityFilter> filters = FilterManager.getFiltersForRepairType();

        Supplier<ListForPage<RepairTypeDTO>> find = () -> {
            List<RepairType> repairs = repairTypeDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
            List<RepairTypeDTO> list = RepairTypeConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForRepairType();

        Supplier<ListForPage<RepairTypeDTO>> find = () -> {
            List<RepairType> repairs = repairTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
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

}
