package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairTypeDTO;

public interface AdminService {

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

    RepairTypeDTO findRepairType(long id);

    void addRepairType(RepairTypeDTO repairType);

    void updateRepairType(RepairTypeDTO repairType);

}
