package it.academy.services.spare_part_order.impl;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.spare_part.impl.SparePartDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.entities.device.Model;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.SparePart;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.ModelsNotFound;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.impl.ModelConverter;
import it.academy.utils.converters.impl.SparePartConverter;
import it.academy.utils.dao.TransactionManger;
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
    private final ModelConverter modelConverter = new ModelConverter();
    private final RepairDAO repairDAO = new RepairDAOImpl(transactionManger);
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl(transactionManger);
    private final SparePartConverter sparePartConverter = new SparePartConverter();
    private final ServiceHelper<SparePart, SparePartDTO> sparePartHelper =
            new ServiceHelper<>(sparePartDAO, SparePart.class, sparePartConverter, transactionManger);

    @Override
    public void createSparePart(ChangeSparePartDTO changeSparePartDTO) {
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
    public void updateSparePart(ChangeSparePartDTO changeSparePartDTO) {
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
    public void deleteSparePart(long id) {
        sparePartHelper.delete(id);
    }


    @Override
    public SparePartForChangeDTO getSparePartForm() {
        return transactionManger.execute(() -> {
            SparePartForChangeDTO sparePartDTO = Builder.buildEmptySparePart();
            List<Model> models = getModelList();
            List<ModelDTO> modelDTOList =modelConverter.convertToDTOList(models);
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
    public SparePartForChangeDTO findSparePart(long id) {
        Supplier<SparePartForChangeDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            if (sparePart == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, SparePart.class);
                transactionManger.rollback();
                throw new ObjectNotFound(SPARE_PART_NOT_FOUND);
            }
            List<ModelDTO> models = modelConverter.convertToDTOList(getModelList());
            return sparePartConverter.convertToDTO(sparePart, models);
        };
        return transactionManger.execute(find);
    }

    @Override
    public List<SparePartDTO> findSparePartsByRepairId(long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            Repair repair = repairDAO.find(id);
            long modelId = repair.getDevice().getModel().getId();
            List<SparePart> spareParts = sparePartDAO.findByModelId(modelId);
            log.info(OBJECTS_FOUND_PATTERN, spareParts.size(), SparePart.class);
            return sparePartConverter.convertToDTOList(spareParts);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input) {
       return sparePartHelper.find(pageNumber, filter, input);
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
