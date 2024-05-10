package it.academy.services.repair;

import it.academy.dto.TablePage;
import it.academy.dto.repair.*;
import it.academy.utils.enums.RepairStatus;

import java.util.Map;

public interface RepairService {

    RepairFormDTO getRepairForm(long brandId);

    void addRepair(CreateRepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    void completeRepair(CompleteRepairDTO repairDTO);

    RepairFormDTO findRepair(long id);

    UserRepairFormDTO findRepairForUser(long id);

//    TablePage<RepairDTO> findRepairs(int pageNumber);

    TablePage<RepairDTO> findForPage(int pageNumber, Map<String, String> userInput);

    TablePage<RepairDTO> findForPageByUserId(long serviceCenterId, int pageNumber, Map<String, String> userInput);

//    TablePage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);
//
//    TablePage<RepairDTO> findRepairsForUser(long serviceCenterId, int pageNumber);
//
//    TablePage<RepairDTO> findRepairsByFilterForUser(long serviceCenterId, int pageNumber, String filter, String userInput);
//
//    TablePage<RepairDTO> findRepairsByStatusForUser(long serviceCenterId, RepairStatus status, int pageNumber);

}
