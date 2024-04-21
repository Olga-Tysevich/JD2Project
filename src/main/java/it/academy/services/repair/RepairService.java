package it.academy.services.repair;


import it.academy.dto.ListForPage;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.*;
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
