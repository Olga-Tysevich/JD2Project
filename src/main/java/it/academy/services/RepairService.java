package it.academy.services;


import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.req.RepairFormReq;
import it.academy.dto.resp.*;
import it.academy.dto.req.BrandDTO;
import it.academy.utils.enums.RepairStatus;

import java.util.List;

public interface RepairService {

    RepairFormDTO getRepairFormData(RepairFormReq repairForm);

    void addRepair(RepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    List<BrandDTO> findBrands();

    RepairTypeDTO findRepairType(long id);

    RepairDTO findRepair(long id);

    ListForPage<ChangeRepairDTO> findRepairs(int pageNumber);

    ListForPage<ChangeRepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    List<RepairTypeDTO> findRepairTypes();

}
