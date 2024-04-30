package it.academy.services.repair;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.*;
import it.academy.utils.enums.RepairStatus;

public interface RepairService {

    RepairFormDTO getRepairFormData(long brandId);

    void addRepair(CreateRepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    ChangeRepairFormDTO findRepair(long id);

    ListForPage<RepairDTO> findRepairs(int pageNumber);

    ListForPage<RepairDTO> findRepairsByFilter(int pageNumber, String filter, String userInput);

    ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    ListForPage<RepairDTO> findRepairsForUser(long serviceCenterId, int pageNumber);

    ListForPage<RepairDTO> findRepairsByFilterForUser(long serviceCenterId, int pageNumber, String filter, String userInput);

    ListForPage<RepairDTO> findRepairsByStatusForUser(long serviceCenterId, RepairStatus status, int pageNumber);

}
