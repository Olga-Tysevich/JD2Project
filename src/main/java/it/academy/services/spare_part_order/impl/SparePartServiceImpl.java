package it.academy.services.spare_part_order.impl;

import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.spare_part.impl.SparePartDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import it.academy.entities.spare_part.SparePart_;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.ModelsNotFound;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.utils.Builder;
import it.academy.utils.PageCounter;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.impl.ModelConverter;
import it.academy.utils.converters.impl.SparePartConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class SparePartServiceImpl implements SparePartService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final ModelConverter modelConverter = new ModelConverter();
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl(transactionManger);
    private final SparePartConverter sparePartConverter = new SparePartConverter();

    @Override
    public void create(ChangeSparePartDTO changeSparePartDTO) {
        checkModels(changeSparePartDTO);
        SparePart sparePart = sparePartConverter.convertToEntity(changeSparePartDTO);
        Supplier<SparePart> create = () -> {
            checkIfExist(sparePart);
            sparePart.setIsActive(true);
            sparePartDAO.create(sparePart);
            List<Long> modelIdList = changeSparePartDTO.getModelIdList();
            addModels(modelIdList, sparePart);
            return sparePart;
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, sparePart);

    }

    @Override
    public void update(ChangeSparePartDTO changeSparePartDTO) {
        checkModels(changeSparePartDTO);
        SparePart sparePart = sparePartConverter.convertToEntity(changeSparePartDTO);
        Supplier<SparePart> create = () -> {
            checkIfExist(sparePart);
            List<Long> modelIdList = changeSparePartDTO.getModelIdList();
            addModels(modelIdList, sparePart);
            return sparePartDAO.update(sparePart);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, sparePart);
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> {
            boolean isDeleted = sparePartDAO.delete(id);
            log.info(OBJECT_DELETED_PATTERN, id, SparePart.class);
            return isDeleted;
        });
    }


    @Override
    public SparePartForChangeDTO getSparePartForm() {
        return transactionManger.execute(() -> {
            SparePartForChangeDTO sparePartDTO = Builder.buildEmptySparePart();
            List<Model> models = getModelList();
            List<ModelDTO> modelDTOList = modelConverter.convertToDTOList(models);
            sparePartDTO.setAllModels(modelDTOList);
            return sparePartDTO;
        });
    }

    @Override
    public SparePartForChangeDTO getSparePartForm(long id) {
        return transactionManger.execute(() -> {
            SparePart sparePart = sparePartDAO.find(id);
            if (sparePart == null) {
                transactionManger.rollback();
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, SparePart.class);
                throw new ObjectNotFound(SPARE_PART_NOT_FOUND);
            }
            SparePartForChangeDTO result = sparePartConverter.convertToChangeDTO(sparePart);
            List<Model> models = getModelList();
            List<ModelDTO> modelDTOList = modelConverter.convertToDTOList(models);
            List<ModelDTO> relatedModels = models.stream()
                    .filter(m -> m.getSpareParts().contains(sparePart))
                    .map(modelConverter::convertToDTO)
                    .collect(Collectors.toList());

            result.setAllModels(modelDTOList);
            result.setRelatedModels(relatedModels);
            return result;
        });
    }

    @Override
    public SparePartForChangeDTO find(long id) {
        Supplier<SparePartForChangeDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            if (sparePart == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, SparePart.class);
                throw new ObjectNotFound(SPARE_PART_NOT_FOUND);
            }
            List<ModelDTO> models = modelConverter.convertToDTOList(getModelList());
            return sparePartConverter.convertToDTO(sparePart, models);
        };
        return transactionManger.execute(find);
    }

    @Override
    public List<SparePartDTO> findSparePartsByModelId(long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            List<SparePart> spareParts = sparePartDAO.findByModelId(id);
            log.info(OBJECTS_FOUND_PATTERN, spareParts.size(), SparePart.class);
            return sparePartConverter.convertToDTOList(spareParts);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<SparePartDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<SparePartDTO>> find = () -> {
            long numberOfEntries = sparePartDAO.getNumberOfEntries();
            List<SparePart> spareParts = sparePartDAO.findForPage(pageNumber, LIST_SIZE);
            List<SparePartDTO> result = sparePartConverter.convertToDTOList(spareParts);
            return new TablePage2<>(result, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<SparePartDTO> findForPageByName(int pageNumber, String input) {
        Supplier<TablePage2<SparePartDTO>> find = () -> {
            long numberOfEntries = sparePartDAO.getNumberOfEntriesByFilter(SparePart_.NAME, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<SparePart> spareParts = sparePartDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, SparePart_.NAME, input);
            List<SparePartDTO> result = sparePartConverter.convertToDTOList(spareParts);
            return new TablePage2<>(result, numberOfEntries);
        };
        return StringUtils.isBlank(input)? findForPage(pageNumber) : transactionManger.execute(find);
    }

    @Override
    public TablePage2<SparePartDTO> findForPageByModelName(int pageNumber, String input) {
        Supplier<TablePage2<SparePartDTO>> find = () -> {
            long numberOfEntries = sparePartDAO.getNumberOfEntriesByModelName(input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<SparePart> spareParts = sparePartDAO.findForPageByModelName(pageNumberForSearch, LIST_SIZE, input);
            List<SparePartDTO> result = sparePartConverter.convertToDTOList(spareParts);
            return new TablePage2<>(result, numberOfEntries);
        };
        return StringUtils.isBlank(input)? findForPage(pageNumber) : transactionManger.execute(find);
    }

    private List<Model> getModelList() {
        List<Model> models = modelDAO.findAll();
        if (models.isEmpty()) {
            transactionManger.rollback();
            log.error(OBJECTS_NOT_FOUND_PATTERN, Model.class);
            throw new ModelsNotFound();
        }
        return models;
    }


    private void addModels(List<Long> modelIdList, SparePart sparePart) {
        modelIdList.forEach(dt -> {
            Model model = modelDAO.find(dt);
            model.addSparePart(sparePart);
            modelDAO.update(model);
        });
    }

    private void checkIfExist(SparePart sparePart) {
        long brandId = sparePart.getId() != null ? sparePart.getId() : 0L;
        if (sparePartDAO.checkIfExist(brandId, sparePart.getName())) {
            log.warn(OBJECT_ALREADY_EXIST, sparePart);
            transactionManger.rollback();
            throw new ObjectAlreadyExist(SPARE_PART_ALREADY_EXIST);
        }
    }

    private void checkModels(ChangeSparePartDTO sparePartDTO) {
        if (sparePartDTO.getModelIdList() != null
                && sparePartDTO.getModelIdList().isEmpty()) {
            throw new IllegalArgumentException(MODELS_NOT_SELECTED);
        }
    }

}
