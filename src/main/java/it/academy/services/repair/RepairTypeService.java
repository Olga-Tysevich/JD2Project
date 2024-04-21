package it.academy.services.repair;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.ListForPage;

public interface RepairTypeService {

    void createRepairType(RepairTypeDTO repairTypeDTO);

    void updateRepairType(RepairTypeDTO repairTypeDTO);

    RepairTypeDTO findRepairType(long id);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

}
