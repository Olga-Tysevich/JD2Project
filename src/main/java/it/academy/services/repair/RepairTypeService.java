package it.academy.services.repair;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.ListForPage;

import java.util.List;

public interface RepairTypeService {

    void createRepairType(RepairTypeDTO repairTypeDTO);

    void updateRepairType(RepairTypeDTO repairTypeDTO);

    void deleteRepairType(long id);

    RepairTypeDTO findRepairType(long id);

    List<RepairTypeDTO> findRepairTypes();

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

}
