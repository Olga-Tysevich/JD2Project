package it.academy.services.device.impl;

import it.academy.dao.device.SparePartDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.device.impl.SparePartDAOImpl;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.*;
import it.academy.dto.resp.SparePartForChangeDTO;
import it.academy.entities.Model;
import it.academy.entities.SparePart;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.ModelsNotFound;
import it.academy.services.device.SparePartService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.SparePartConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
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
    public void createSparePart(ChangeSparePartDTO changeSparePartDTO) {
        checkModels(changeSparePartDTO);
        SparePart sparePart = SparePartConverter.convertToEntity(changeSparePartDTO);
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
    public void updateSparePart(ChangeSparePartDTO changeSparePartDTO) {
        checkModels(changeSparePartDTO);
        SparePart sparePart = SparePartConverter.convertToEntity(changeSparePartDTO);
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
    public SparePartForChangeDTO getSparePartForm() {
        return transactionManger.execute(() -> {
            SparePartForChangeDTO sparePartDTO = Builder.buildEmptySparePart();
            List<Model> models = getModelList();
            List<ModelDTO> modelDTOList = ModelConverter.convertToDTOList(models);
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
                throw  new ObjectNotFound(SPARE_PART_NOT_FOUND);
            }
            SparePartForChangeDTO result = SparePartConverter.convertToChangeDTO(sparePart);
            List<Model> models = getModelList();
            List<ModelDTO> modelDTOList = ModelConverter.convertToDTOList(models);
            List<ModelDTO> relatedModels = models.stream()
                    .filter(m -> m.getSpareParts().contains(sparePart))
                    .map(ModelConverter::convertToDTO)
                    .collect(Collectors.toList());

            result.setAllModels(modelDTOList);
            result.setRelatedModels(relatedModels);
            return result;
        });
    }

    @Override
    public SparePartForChangeDTO findSparePart(long id) {
        Supplier<SparePartForChangeDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            if (sparePart == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, SparePart.class);
                transactionManger.rollback();
                throw new ObjectNotFound(SPARE_PART_NOT_FOUND);
            }
            List<ModelDTO> models = ModelConverter.convertToDTOList(getModelList());
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

    @Override
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber) {
        long numberOfEntries = sparePartDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> sparePartDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input) {
        long numberOfEntries = sparePartDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> sparePartDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<SparePartDTO> find(Supplier<List<SparePart>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<SparePart> spareParts = ServiceHelper.getList(transactionManger, methodForSearch, SparePart.class);
        List<EntityFilter> filters = FilterManager.getFiltersForSparePart();
        List<SparePartDTO> listDTO = SparePartConverter.convertToDTOList(spareParts);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
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
        if (sparePartDAO.checkIfComponentExist(brandId, sparePart.getName())) {
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
