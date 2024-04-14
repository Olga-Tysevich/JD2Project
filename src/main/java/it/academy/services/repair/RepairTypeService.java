package it.academy.services.repair;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.resp.ListForPage;

public interface RepairTypeService {

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

    RepairTypeDTO findRepairType(long id);

    void addRepairType(RepairTypeDTO repairType);

    void updateRepairType(RepairTypeDTO repairType);

}
