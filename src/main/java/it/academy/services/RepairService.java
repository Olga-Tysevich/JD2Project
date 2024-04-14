package it.academy.services;


import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairTypeDTO;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.DeviceDTOResp;
import it.academy.dto.resp.ListForPage;
import it.academy.utils.enums.RepairStatus;

import java.util.List;

public interface RepairService {

    void addRepair(RepairDTO repairDTO);

    void updateRepair(RepairDTO repairDTO);

    List<BrandDTO> findBrands();

    DeviceDTOResp addDevice(DeviceDTOResp deviceDTOResp);

    DeviceDTOResp updateDevice(DeviceDTOResp deviceDTOResp);

    DeviceDTOResp findDevice(long id);

//    CreateModelDTO findModel(long id);

    BrandDTO findBrand(long id);

    RepairTypeDTO findRepairType(long id);

//    List<CreateModelDTO> findModelsByBrandId(long brandId);

    RepairDTO findRepair(long id);

    ListForPage<RepairDTO> findRepairs(int pageNumber);

    ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

    List<RepairTypeDTO> findRepairTypes();

}
