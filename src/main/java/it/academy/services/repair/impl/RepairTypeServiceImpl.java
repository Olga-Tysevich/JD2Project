package it.academy.services.repair.impl;

import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.repair.RepairType;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.repair.RepairTypeService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.RepairTypeConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class RepairTypeServiceImpl implements RepairTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);

    @Override
    public void create(RepairTypeDTO repairTypeDTO) {
        checkRepairTypeName(repairTypeDTO.getName());
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        transactionManger.execute(() -> {
            checkRepairType(ID_FOR_CHECK, repairType.getName());
            return repairTypeDAO.create(repairType);
        });
        log.info(OBJECT_CREATED_PATTERN, repairType);
    }

    @Override
    public void update(RepairTypeDTO repairTypeDTO) {
        checkRepairTypeName(repairTypeDTO.getName());
        RepairType repairType = RepairTypeConverter.convertToEntity(repairTypeDTO);
        transactionManger.execute(() -> {
            checkRepairType(repairType.getId(), repairType.getName());
            return repairTypeDAO.update(repairType);
        });
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

//    @Override
//    public TablePage<RepairTypeDTO> findForPage(int pageNumber) {
//        Supplier<TablePage<RepairTypeDTO>> find = () -> {
//            long numberOfEntries = repairTypeDAO.getNumberOfEntries();
//            List<RepairType> result = repairTypeDAO.findForPage(pageNumber, LIST_SIZE);
//            List<RepairTypeDTO> dtoList = RepairTypeConverter.convertToDTOList(result);
//            return new TablePage<>(dtoList, numberOfEntries);
//        };
//        return transactionManger.execute(find);
//    }
//
//    @Override
//    public TablePage<RepairTypeDTO> findForPageByFilter(int pageNumber, String filter, String input) {
//        Supplier<TablePage<RepairTypeDTO>> find = () -> {
//            long numberOfEntries = repairTypeDAO.getNumberOfEntriesByFilter(filter, input);
//            int pageForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
//            List<RepairType> result = repairTypeDAO.findForPageByAnyMatch(pageForSearch, LIST_SIZE, filter, input);
//            List<RepairTypeDTO> dtoList = RepairTypeConverter.convertToDTOList(result);
//            return new TablePage<>(dtoList, numberOfEntries);
//        };
//        return StringUtils.isBlank(input)? findForPage(pageNumber) : transactionManger.execute(find);
//    }

    @Override
    public TablePage<RepairTypeDTO> findForPage(int pageNumber, Map<String, String> userInput) {
        Supplier<TablePage<RepairTypeDTO>> find = () -> {
            long numberOfEntries = repairTypeDAO.getNumberOfEntries(userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<RepairType> brands = repairTypeDAO.findAllByPageAndFilter(pageNumberForSearch, LIST_SIZE, userInput);
            List<RepairTypeDTO> dtoList = RepairTypeConverter.convertToDTOList(brands);
            return new TablePage<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkRepairTypeName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException(NAME_IS_EMPTY);
        }
    }

    private void checkRepairType(long id, String name) {
        boolean isExist = repairTypeDAO.checkIfExist(id, name);
        if (isExist) {
            throw new ObjectAlreadyExist(REPAIR_TYPE_ALREADY_EXIST);
        }
    }

}
