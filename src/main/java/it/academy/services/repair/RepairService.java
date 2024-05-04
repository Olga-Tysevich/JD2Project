package it.academy.services.repair;

import it.academy.dto.TablePage2;
import it.academy.dto.repair.*;
import it.academy.utils.enums.RepairStatus;

public interface RepairService {

    RepairFormDTO getRepairForm(long brandId);

    void addRepair(CreateRepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    void completeRepair(CompleteRepairDTO repairDTO);

    RepairFormDTO findRepair(long id);

    UserRepairFormDTO findRepairForUser(long id);

    TablePage2<RepairDTO> findRepairs(int pageNumber);

    TablePage2<RepairDTO> findRepairsByFilter(int pageNumber, String filter, String userInput);

    TablePage2<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    TablePage2<RepairDTO> findRepairsForUser(long serviceCenterId, int pageNumber);

    TablePage2<RepairDTO> findRepairsByFilterForUser(long serviceCenterId, int pageNumber, String filter, String userInput);

    TablePage2<RepairDTO> findRepairsByStatusForUser(long serviceCenterId, RepairStatus status, int pageNumber);

}
