package it.academy.services.repair;


import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.BrandDTO;
import it.academy.dto.device.resp.DeviceDTOResp;
import it.academy.dto.device.req.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairStatus;

import java.util.List;

public interface RepairService {

    void addRepair(RepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    List<BrandDTO> findBrands();

    DeviceDTOResp addDevice(DeviceDTOResp deviceDTOResp);

    DeviceDTOResp updateDevice(DeviceDTOResp deviceDTOResp);

    DeviceDTOResp findDevice(long id);

    ModelDTO findModel(long id);

    BrandDTO findBrand(long id);

    RepairTypeDTO findRepairType(long id);

    List<ModelDTO> findModelsByBrandId(long brandId);

    RepairDTO findRepair(long id);

    ListForPage<RepairDTO> findRepairs(int pageNumber);

    ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    List<RepairTypeDTO> findRepairTypes();

}
