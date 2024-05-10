package it.academy.services.spare_part_order.impl;

import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.spare_part.impl.SparePartDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.utils.PageCounter;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.SparePartConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class SparePartServiceImpl implements SparePartService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl(transactionManger);

    @Override
    public void create(SparePartDTO sparePartDTO) {
        checkModels(sparePartDTO);
        SparePart sparePart = SparePartConverter.convertToEntity(sparePartDTO);
        Supplier<SparePart> create = () -> {
            checkIfExist(ID_FOR_CHECK, sparePart);
            sparePartDAO.create(sparePart);
            List<Long> modelIdList = sparePartDTO.getModelIdList();
            addModels(modelIdList, sparePart);
            sparePartDAO.update(sparePart);
            return sparePart;
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, sparePart);

    }

    @Override
    public void update(SparePartDTO sparePartDTO) {
        checkModels(sparePartDTO);
        SparePart sparePart = SparePartConverter.convertToEntity(sparePartDTO);
        Supplier<SparePart> create = () -> {
            checkIfExist(sparePart.getId(), sparePart);
            List<Long> modelIdList = sparePartDTO.getModelIdList();
            addModels(modelIdList, sparePart);
            sparePartDAO.update(sparePart);
            return sparePartDAO.update(sparePart);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, sparePart);
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> sparePartDAO.delete(id));
    }


    @Override
    public SparePartDTO find(long id) {
        Supplier<SparePartDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            List<Long> models = sparePart.getModels().stream().map(Model::getId).collect(Collectors.toList());
            return SparePartConverter.convertToDTO(sparePart, models);
        };
        return transactionManger.execute(find);
    }

    @Override
    public List<SparePartDTO> findSparePartsByModelId(long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            List<SparePart> spareParts = sparePartDAO.findByModelId(id);
            log.info(OBJECTS_FOUND_PATTERN, spareParts.size(), SparePart.class);
            return SparePartConverter.convertToDTOList(spareParts);
        };
        return transactionManger.execute(find);
    }

//    @Override
//    public TablePage<SparePartDTO> findForPage(int pageNumber) {
//        Supplier<TablePage<SparePartDTO>> find = () -> {
//            long numberOfEntries = sparePartDAO.getNumberOfEntries();
//            List<SparePart> spareParts = sparePartDAO.findForPage(pageNumber, LIST_SIZE);
//            List<SparePartDTO> result = SparePartConverter.convertToDTOList(spareParts);
//            return new TablePage<>(result, numberOfEntries);
//        };
//        return transactionManger.execute(find);
//    }

//    @Override
//    public TablePage<SparePartDTO> findForPageByName(int pageNumber, String input) {
//        Supplier<TablePage<SparePartDTO>> find = () -> {
//            long numberOfEntries = sparePartDAO.getNumberOfEntriesByFilter(SparePart_.NAME, input);
//            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
//            List<SparePart> spareParts = sparePartDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, SparePart_.NAME, input);
//            List<SparePartDTO> result = SparePartConverter.convertToDTOList(spareParts);
//            return new TablePage<>(result, numberOfEntries);
//        };
//        return StringUtils.isBlank(input) ? findForPage(pageNumber) : transactionManger.execute(find);
//    }
//
//    @Override
//    public TablePage<SparePartDTO> findForPageByModelName(int pageNumber, String input) {
//        Supplier<TablePage<SparePartDTO>> find = () -> {
//            long numberOfEntries = sparePartDAO.getNumberOfEntriesByModelName(input);
//            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
//            List<SparePart> spareParts = sparePartDAO.findForPageByModelName(pageNumberForSearch, LIST_SIZE, input);
//            List<SparePartDTO> result = SparePartConverter.convertToDTOList(spareParts);
//            return new TablePage<>(result, numberOfEntries);
//        };
//        return StringUtils.isBlank(input) ? findForPage(pageNumber) : transactionManger.execute(find);
//    }

    @Override
    public TablePage<SparePartDTO> findForPage(int pageNumber, Map<String, String> userInput) {
        Supplier<TablePage<SparePartDTO>> find = () -> {
            long numberOfEntries = sparePartDAO.getNumberOfEntries(userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<SparePart> spareParts = sparePartDAO.findAllByPageAndFilter(pageNumberForSearch, LIST_SIZE, userInput);
            List<SparePartDTO> dtoList = SparePartConverter.convertToDTOList(spareParts);
            return new TablePage<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void addModels(List<Long> modelIdList, SparePart sparePart) {
        modelIdList.forEach(dt -> {
            Model model = modelDAO.find(dt);
            model.addSparePart(sparePart);
        });
    }

    private void checkIfExist(long id, SparePart sparePart) {
        if (sparePartDAO.checkIfExist(id, sparePart.getName())) {
            log.warn(OBJECT_ALREADY_EXIST, sparePart);
            throw new ObjectAlreadyExist(SPARE_PART_ALREADY_EXIST);
        }
    }

    private void checkModels(SparePartDTO sparePartDTO) {
        if (sparePartDTO.getModelIdList() == null
                || sparePartDTO.getModelIdList().isEmpty()) {
            throw new IllegalArgumentException(MODELS_NOT_SELECTED);
        }
    }

}
