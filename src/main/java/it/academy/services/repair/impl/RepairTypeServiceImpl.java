package it.academy.services.repair.impl;

import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.RepairType;
import it.academy.services.repair.RepairTypeService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.RepairTypeConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.LIST_SIZE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class RepairTypeServiceImpl implements RepairTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);

    @Override
    public void create(RepairTypeDTO repairTypeDTO) {
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        transactionManger.execute(() -> repairTypeDAO.create(repairType));
        log.info(OBJECT_CREATED_PATTERN, repairType);
    }

    @Override
    public void update(RepairTypeDTO repairTypeDTO) {
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        transactionManger.execute(() -> repairTypeDAO.update(repairType));
        log.info(OBJECT_UPDATED_PATTERN, repairTypeDTO);
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> repairTypeDAO.delete(id));
    }

    @Override
    public RepairTypeDTO find(long id) {
        RepairType result = transactionManger.execute(() -> repairTypeDAO.find(id));
        return RepairTypeConverter.convertToDTO(result);
    }


    @Override
    public List<RepairTypeDTO> findAll() {
        List<RepairType> result = transactionManger.execute(repairTypeDAO::findAll);
        return RepairTypeConverter.convertToDTOList(result);
    }

    @Override
    public TablePage2<RepairTypeDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<RepairTypeDTO>> find = () -> {
            long numberOfEntries = repairTypeDAO.getNumberOfEntries();
            List<RepairType> result = repairTypeDAO.findForPage(pageNumber, LIST_SIZE);
            List<RepairTypeDTO> dtoList = RepairTypeConverter.convertToDTOList(result);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<RepairTypeDTO> findForPageByFilter(int pageNumber, String filter, String input) {
        Supplier<TablePage2<RepairTypeDTO>> find = () -> {
            long numberOfEntries = repairTypeDAO.getNumberOfEntriesByFilter(filter, input);
            int pageForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<RepairType> result = repairTypeDAO.findForPageByAnyMatch(pageForSearch, LIST_SIZE, filter, input);
            List<RepairTypeDTO> dtoList = RepairTypeConverter.convertToDTOList(result);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return StringUtils.isBlank(input)? findForPage(pageNumber) : transactionManger.execute(find);
    }

}
