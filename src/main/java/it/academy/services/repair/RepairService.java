package it.academy.services.repair;


import it.academy.dto.ListForPage;
import it.academy.dto.repair.*;
import it.academy.utils.enums.RepairStatus;

public interface RepairService {

    RepairFormDTO getRepairFormData(long brandId);

    void addRepair(CreateRepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    ChangeRepairFormDTO findRepair(long id);

    ListForPage<RepairForTableDTO> findRepairs(int pageNumber);

    ListForPage<RepairForTableDTO> findRepairs(int pageNumber, String filter, String userInput);

    ListForPage<RepairForTableDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

}
