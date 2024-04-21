package it.academy.services.repair.impl;

import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.repair.RepairType;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.repair.RepairTypeService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.repair.RepairTypeConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_ALREADY_EXIST;
import static it.academy.utils.constants.LoggerConstants.OBJECT_NOT_FOUND_PATTERN;

@Slf4j
public class RepairTypeServiceImpl implements RepairTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);

    @Override
    public void createRepairType(RepairTypeDTO repairTypeDTO) {
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        Supplier<RepairType> create = () -> {
            checkIfExist(repairType);
            return repairTypeDAO.create(repairType);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, repairType);
    }

    @Override
    public void updateRepairType(RepairTypeDTO repairTypeDTO) {
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        Supplier<RepairType> update = () -> {
            checkIfExist(repairType);
            return repairTypeDAO.update(repairType);
        };
        transactionManger.execute(update);
        log.info(LoggerConstants.OBJECT_UPDATED_PATTERN, repairType);
    }

    @Override
    public RepairTypeDTO findRepairType(long id) {
        RepairType repairType = transactionManger.execute(() -> {
            return repairTypeDAO.find(id);
        });
        if (repairType == null) {
            log.warn(OBJECT_NOT_FOUND_PATTERN, id, RepairType.class);
            throw new ObjectNotFound(BRAND_NOT_FOUND);
        }
        return RepairTypeConverter.convertToDTO(repairType);
    }


    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber) {
        long numberOfEntries = repairTypeDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> repairTypeDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input) {
        long numberOfEntries = repairTypeDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> repairTypeDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<RepairTypeDTO> find(Supplier<List<RepairType>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<RepairType> repairTypes = ServiceHelper.getList(transactionManger, methodForSearch, RepairType.class);
        List<EntityFilter> filters = FilterManager.getFiltersForRepairType();
        List<RepairTypeDTO> listDTO = RepairTypeConverter.convertToDTOList(repairTypes);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    private void checkIfExist(RepairType repairType) {
        long repairTypeId = repairType.getId() != null ? repairType.getId() : 0L;
        if (repairTypeDAO.checkIfComponentExist(repairTypeId, repairType.getName())) {
            log.warn(OBJECT_ALREADY_EXIST, repairType);
            transactionManger.rollback();
            throw new ObjectAlreadyExist(REPAIR_TYPE_ALREADY_EXIST);
        }
    }

}
