package it.academy.services;

import it.academy.dto.resp.RepairTypeDTO;
import it.academy.dto.resp.ListForPage;

public interface RepairTypeService {

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

    RepairTypeDTO findRepairType(long id);

    void addRepairType(RepairTypeDTO repairType);

    void updateRepairType(RepairTypeDTO repairType);

}
