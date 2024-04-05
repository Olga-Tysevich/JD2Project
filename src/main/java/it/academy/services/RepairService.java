package it.academy.services;


import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairStatus;

import java.util.List;

public interface RepairService {

    void addRepair(RepairDTO repairDTO);

    List<BrandDTO> findBrands();

    DeviceDTO addDevice(DeviceDTO deviceDTO);

    ModelDTO findModel(long id);

    List<ModelDTO> findModelsByBrandId(long brandId);

    ListForPage<RepairDTO> findRepairs(int pageNumber);

    ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

}
