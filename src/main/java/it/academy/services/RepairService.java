package it.academy.services;


import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.resp.*;
import it.academy.utils.enums.RepairStatus;

import java.util.List;

public interface RepairService {

    RepairFormDTO getRepairFormData(AccountDTO currentAccount, long brandId);

    void addRepair(RepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    ChangeRepairFormDTO findRepair(AccountDTO currentAccount, long id);

    ListForPage<ChangeRepairDTO> findRepairs(int pageNumber);

    ListForPage<ChangeRepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    List<RepairTypeDTO> findRepairTypes();

}
