package it.academy.services.repair.impl;

import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.TablePage;
import it.academy.entities.repair.RepairType;
import it.academy.services.repair.RepairTypeService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.RepairTypeConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class RepairTypeServiceImpl implements RepairTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);
    private final RepairTypeConverter repairTypeConverter = new RepairTypeConverter();
    private final ServiceHelper<RepairType, RepairTypeDTO> repairTypeHelper =
            new ServiceHelper<>(repairTypeDAO, RepairType.class, repairTypeConverter, transactionManger);

    @Override
    public void createRepairType(RepairTypeDTO repairTypeDTO) {
        repairTypeHelper.create(repairTypeDTO, () ->
                repairTypeDAO.checkIfExist(ID_FOR_CHECK, repairTypeDTO.getName()));
    }

    @Override
    public void updateRepairType(RepairTypeDTO repairTypeDTO) {
        repairTypeHelper.update(repairTypeDTO, () ->
                repairTypeDAO.checkIfExist(repairTypeDTO.getId(), repairTypeDTO.getName()));
    }

    @Override
    public void deleteRepairType(long id) {
        repairTypeHelper.delete(id);
    }

    @Override
    public RepairTypeDTO findRepairType(long id) {
        return repairTypeHelper.find(id);
    }


    @Override
    public List<RepairTypeDTO> findRepairTypes() {
        return repairTypeHelper.findAll();
    }

    @Override
    public TablePage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input) {
        return repairTypeHelper.find(pageNumber, filter, input);
    }

}
